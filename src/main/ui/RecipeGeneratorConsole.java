package ui;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//represents the console seen by the user
public class RecipeGeneratorConsole {
    private Scanner scanner;
    private RecipeGenerator generator;
    private Boolean shouldProgramContinueRunning;

    private static final String FILE_LOCATION = "./data/RecipeGenerator.json";

    public RecipeGeneratorConsole() {
        scanner = new Scanner(System.in);
        generator = new RecipeGenerator();
        shouldProgramContinueRunning = true;
    }


    //EFFECTS: accepts choice from user and displays functionality related to the choice
    public void mainInterface() {
        emptyLines(1);
        System.out.println("RECIPE GENERATOR");
        emptyLines(1);
        System.out.println("Please enter a number corresponding to the option you wish to select: ");
        System.out.println("[1] Add an ingredient to available ingredients.");
        System.out.println("[2] Add quantity to already available ingredient. ");
        System.out.println("[3] See all available ingredients with quantity. ");
        System.out.println("[4] Add Recipe to Recipe List");
        System.out.println("[5] Look up Recipe with name ");
        System.out.println("[6] See all Recipes you can cook with available ingredients. ");
        System.out.println("[7] Save recipes and ingredients to file. ");
        System.out.println("[8] Load saved data. ");
        System.out.println("[9] Quit");

        emptyLines(1);
        System.out.print("Enter your choice: ");
        int n = acceptInputDouble().intValue();
        emptyLines(1);
        choice(n);
    }

    //EFFECTS: displays functionality related to the choice
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void choice(int n) {
        switch (n) {
            case 1:
                addIngredientToAvailableIngredients();
                break;
            case 2:
                addQuantityToIngredient();
                break;
            case 3:
                showIngredients();
                break;
            case 4:
                createRecipe();
                break;
            case 5:
                viewRecipe();
                break;
            case 6:
                showCookableRecipe();
                break;
            case 7:
                writeToFile();
                break;
            case 8:
                readSavedFile();
                break;
            case 9:
                stopProgram();
                break;
            default:
                System.out.println("Invalid Input. Please enter a valid option");
        }
    }

    //MODIFIES: this
    //EFFECTS: Accepts Ingredient from user and adds it to availableIngredients with quantity 0
    private void addIngredientToAvailableIngredients() {
        System.out.println("Adding Ingredient to Available Ingredients.");
        System.out.print("Enter name of Ingredient: ");
        String str = acceptInputString();
        generator.createIngredient(str);
        System.out.printf("Added %s successfully to available ingredients with no quantity.%n",str);
    }

    //MODIFIES: this
    //EFFECTS: Accepts name from user and adds quantity to ingredient with name if in list
    private void addQuantityToIngredient() {
        System.out.println();
        System.out.println("Adding Quantity to Ingredients.");
        System.out.print("Enter name of Ingredient: ");
        String name = acceptInputString();
        System.out.print("Enter Quantity to add: ");
        Double quantity = acceptInputDouble();
        Boolean success = generator.addQuantityToIngredient(name,quantity);
        if (success) {
            System.out.printf("%.2f %s added successfully to available ingredients %n", quantity, name);
        } else {
            System.out.printf("%s not found in available ingredients%n", name);
        }
    }

    //EFFECTS: shows all available ingredients with quantity
    private void showIngredients() {
        System.out.println("Showing available ingredients with quantity");
        emptyLines(1);
        for (Ingredient ingredient : generator.getAvailableIngredients()) {
            System.out.println(ingredient.printable());
        }
    }

    //MODIFIES: this
    //EFFECTS: gets user to input name, ingredients(have to be in availableIngredients), and steps
    //         and adds recipe to RecipeList
    private void createRecipe() {
        System.out.println("Creating Recipe: ");
        System.out.print("Enter name of Recipe: ");
        String name = acceptInputString();
        ArrayList<Ingredient> ingredientArrayList = getIngredientListFromUser();
        ArrayList<String> recipeStepList = getRecipeStepsFromUser();
        Recipe recipe = new Recipe(name,ingredientArrayList,recipeStepList);
        generator.addRecipe(recipe);
    }

    //EFFECTS: allows user to view recipe with name given by user
    private void viewRecipe() {
        System.out.println("Search recipe.");
        System.out.print("Enter name of recipe: ");
        String name = acceptInputString();
        emptyLines(1);
        boolean b = false;
        for (Recipe recipe : generator.getRecipeList()) {
            if (recipe.getName().equals(name)) {
                System.out.println(recipe.recipePrintable());
                b = true;
                break;
            }
        }
        if (!b) {
            System.out.println("Recipe not found.");
        }
    }

    //EFFECTS: allows user to view recipe that can be cooked with ingredients with enough quantity available
    private void showCookableRecipe() {
        System.out.println();
        System.out.println("Showing recipes which you can cook right now");
        emptyLines(2);
        if (generator.getCookableRecipes().size() != 0) {
            for (Recipe recipe : generator.getCookableRecipes()) {
                System.out.println(recipe.getName());
            }
        } else {
            System.out.println("No recipes can be cooked with ingredients available.");
        }
    }

    //MODIFIES: this
    //EFFECTS: sets shouldProgram continue running to false
    private void stopProgram() {
        shouldProgramContinueRunning = false;
        emptyLines(1);
        System.out.println("Quitting. See ya later!!");
        emptyLines(1);
    }

    //EFFECTS: accepts string from user and returns said string
    private String acceptInputString() {
        try {
            String str = scanner.nextLine();
            if (Objects.equals(str.strip(), "")) {
                System.out.print("Please enter a valid input: ");
                return acceptInputString();
            } else {
                return str.strip();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please re-enter your choice with valid characters");
            return acceptInputString();
        }
    }

    //EFFECTS: accepts string from user and returns said string
    private Double acceptInputDouble() {
        try {
            double d = scanner.nextDouble();
            scanner.nextLine();
            return d;
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println();
            System.out.print("Please re-enter your choice with valid numbers: ");
            return acceptInputDouble();
        }
    }

    //EFFECTS: prints empty lines n times
    private void emptyLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    //EFFECTS: gets ingredients with quantities from user
    private ArrayList<Ingredient> getIngredientListFromUser() {
        System.out.print("Enter number of ingredients in recipe: ");
        int n = acceptInputDouble().intValue();
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of ingredient: ");
            String ingredientName = acceptInputString();
            System.out.print("Enter quantity of ingredient needed: ");
            Double quantity = acceptInputDouble();
            Ingredient ingredient = new Ingredient(ingredientName,quantity);
            ingredientArrayList.add(ingredient);
        }
        return ingredientArrayList;
    }

    //EFFECTS: gets recipe steps from user and returns an array of string
    private ArrayList<String> getRecipeStepsFromUser() {
        System.out.print("Enter number of steps in Recipe: ");
        int n = acceptInputDouble().intValue();
        ArrayList<String> recipeStepList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter Step %d: ",i);
            String recipeStep = acceptInputString();
            recipeStepList.add(recipeStep);
        }
        return recipeStepList;
    }

    //EFFECTS: writes generator to FILE_LOCATION
    private void writeToFile() {
        try {
            JsonWriter writer = new JsonWriter(FILE_LOCATION);
            writer.open();
            writer.write(generator);
            writer.close();
            System.out.println("Successfully written to file.");
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written");

        }
    }

    //MODIFIES: this
    //EFFECTS: reads saved file at FILE_LOCATION
    private void readSavedFile() {
        try {
            JsonReader reader = new JsonReader(FILE_LOCATION);
            generator = reader.read();
            System.out.println("Read from file successful.");
        } catch (IOException e) {
            System.out.println("Please save to file first and try again.");
        }
    }

    public Boolean getShouldProgramContinueRunning() {
        return shouldProgramContinueRunning;
    }
}
