package model;

import java.util.List;

//represents a Recipe as a collection of Ingredients with
public class Recipe {

    public Recipe(String name) {
        //stub
    }

    public Recipe(String name, List<Ingredient> ingredientList, List<String> recipeInstructions) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to ingredientList
    public void addIngredient(Ingredient ingredient){
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds recipe step to recipeInstructions
    public void addRecipeStep(String recipeStep){
        //stub
    }

    //EFFECTS: checks if available ingredients are sufficient to cook
    public Boolean canRecipeBeMade(List<Ingredient> availableIngredients) {
        return false;
    }

    //EFFECTS: returns formatted String to print recipe
    public String recipePrintable() {
        return null;
    }

    //EFFECTS: checks if Ingredient is in Recipe
    private Boolean isIngredientInRecipe(Ingredient ingredient) {
        return false;
    }

    public String getName() {
        return null;
    }

    public List<Ingredient> getIngredientList() {
        return null;
    }

    public List<String> getRecipeInstructions() {
        return null;
    }
}
