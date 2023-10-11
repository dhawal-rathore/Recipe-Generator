package ui;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;

import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.Scanner;

//represents the console seen by the user
public class RecipeGeneratorConsole {
    private Scanner scanner;
    private RecipeGenerator generator;

    private Boolean shouldProgramContinueRunning;

    public RecipeGeneratorConsole() {
        scanner = new Scanner(System.in);
        generator = new RecipeGenerator();
        shouldProgramContinueRunning = true;
    }


    //EFFECTS: accepts choice from user and displays functionality related to the choice
    public void mainInterface() {
        emptyLines(2);
        System.out.println("RECIPE GENERATOR");
        emptyLines(2);
        System.out.println("Please enter a number corresponding to the option you wish to select: ");
        System.out.println("[1] Add an ingredient to available ingredients.");
        System.out.println("[2] Add quantity to already available ingredient. ");
        System.out.println("[3] See all available ingredients with quantity. ");
        System.out.println("[4] Add Recipe to Recipe List");
        System.out.println("[5] Look up Recipe with name ");
        System.out.println("[6] See all Recipes you can cook with available ingredients. ");
        System.out.println("[7] Quit");

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
                stopProgram();
                break;
            default:
                System.out.println("Invalid Input. Please enter a valid option");
        }
    }

    //MODIFIES: this
    //EFFECTS: Accepts Ingredient from user and adds it to availableIngredients with quantity 0
    private void addIngredientToAvailableIngredients() {
        System.out.println();
        System.out.println("Adding Ingredient to Available Ingredients.");
        System.out.print("Enter name of Ingredient: ");
        String str = acceptInputString();
        generator.createIngredient(str);
        System.out.println(String.format("Added %s successfully to available ingredients with no quantity.",str));
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
            System.out.println(String.format("%.2f %s added successfully to available ingredients ", quantity, name));
        } else {
            System.out.println(String.format("%s not found in available ingredients", name));
        }
    }

    //EFFECTS: shows all available ingredients with quantity
    private void showIngredients() {
        System.out.println("Showing available ingredients with quantity");
        emptyLines(2);
        for (Ingredient ingredient : generator.getAvailableIngredients()) {
            System.out.println(ingredient.printable());
        }
    }

    //MODIFIES: this
    //EFFECTS: gets user to input name, ingredients(have to be in availableIngredients), and steps
    //         and adds recipe to RecipeList
    private void createRecipe() {
        System.out.println();
        System.out.println("Creating Recipe: ");
        System.out.print("Enter name of Recipe: ");
        String name = acceptInputString();
        ArrayList<Ingredient> ingredientArrayList = getIngredientListFromUser();
        ArrayList<String> recipeStepList = getRecipeStepsFromUser();
        Recipe recipe = new Recipe(name,ingredientArrayList,recipeStepList);
        generator.addRecipe(recipe);
    }

    //MODIFIES: this
    //EFFECTS: allows user to view recipe with name given by user
    private void viewRecipe() {
        System.out.println();
        System.out.println("Search recipe.");
        System.out.print("Enter name of Recipe: ");
        String name = acceptInputString();
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
        System.out.println("Showing Recipes which you can cook right now");
        emptyLines(2);
        for (Recipe recipe : generator.getCookableRecipes()) {
            System.out.println(recipe.getName());
        }

    }

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
            if (str.strip() == "") {
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

    private void emptyLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    private ArrayList<Ingredient> getIngredientListFromUser() {
        System.out.print("Enter number of ingredients in Recipe: ");
        int n = acceptInputDouble().intValue();
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of Ingredient: ");
            String ingredientName = acceptInputString();
            System.out.print("Enter Quantity of Ingredient needed: ");
            Double quantity = acceptInputDouble();
            Ingredient ingredient = new Ingredient(ingredientName,quantity);
            ingredientArrayList.add(ingredient);
        }
        return  ingredientArrayList;
    }

    private ArrayList<String> getRecipeStepsFromUser() {
        System.out.print("Enter number of steps in Recipe: ");
        int n = acceptInputDouble().intValue();
        ArrayList<String> recipeStepList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Step: ");
            String recipeStep = acceptInputString();
            recipeStepList.add(recipeStep);
        }
        return recipeStepList;
    }

    public Boolean getShouldProgramContinueRunning() {
        return shouldProgramContinueRunning;
    }
}
