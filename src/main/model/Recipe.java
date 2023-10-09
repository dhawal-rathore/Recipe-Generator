package model;

import java.util.ArrayList;
import java.util.List;

//represents a Recipe as a collection of Ingredients with
public class Recipe {

    private String name;
    private List<Ingredient> ingredientList;
    private List<String> recipeInstructions;


    public Recipe(String name) {
        this.name = name;
        this.ingredientList = new ArrayList<>();
        this.recipeInstructions = new ArrayList<>();
    }

    public Recipe(String name, List<Ingredient> ingredientList, List<String> recipeInstructions) {
        this.name = name;
        this.ingredientList = ingredientList;
        this.recipeInstructions = recipeInstructions;
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to ingredientList
    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    //MODIFIES: this
    //EFFECTS: adds recipe step to recipeInstructions
    public void addRecipeStep(String recipeStep) {
        recipeInstructions.add(recipeStep);
    }

    //EFFECTS: checks if available ingredients are sufficient to cook
    public Boolean canRecipeBeMade(List<Ingredient> availableIngredients) {
        Boolean b = true;
        for (Ingredient recipeIngredient : ingredientList) {
            int ingredientIndex = getIndexOfIngredient(recipeIngredient,availableIngredients);
            System.out.println(ingredientIndex);
            if (ingredientIndex != -1) {
                if (availableIngredients.get(ingredientIndex).hasQuantityMoreThan(recipeIngredient)) {
                    continue;
                } else {
                    b = false;
                    break;
                }
            } else {
                b = false;
                break;
            }
        }
        return b;
    }

    private int getIndexOfIngredient(Ingredient recipeIngredient, List<Ingredient> availableIngredients) {
        for (int i = 0; i < availableIngredients.size(); i++) {
            if (availableIngredients.get(i).equals(recipeIngredient)) {
                return i;
            }
        }
        return -1;
    }

    //EFFECTS: returns formatted String to print recipe
    public String recipePrintable() {
        String recipeString = "";
        recipeString += String.format("Name: %s\n",name);
        recipeString += "Ingredients:\n\n";
        for (Ingredient ingredient : ingredientList) {
            recipeString += String.format("%.1f %s\n",ingredient.getQuantity(),ingredient.getName());
        }
        recipeString += "\nSteps:\n";
        for (int i = 0; i < recipeInstructions.size(); i++) {
            recipeString += String.format("\n%d. %s", i + 1,recipeInstructions.get(i));
        }
        return recipeString;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<String> getRecipeInstructions() {
        return recipeInstructions;
    }

}
