package model;

import java.util.List;

public class RecipeGenerator {

    //MODIFIES: this
    //EFFECTS: adds Ingredient to available ingredients with quantity 0
    public void createIngredient(String name) {
        //stub
    }

    //REQUIRES: quantity > 0
    //MODIFIES: this
    //EFFECTS: adds quantity to Ingredient quantity if ingredient with name in availableIngredients and returns true
    //         else returns false
    public Boolean addQuantityToIngredient(String name, double quantity) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: creates recipe and adds to recipeList
    public void addRecipe(String recipeName, List<Ingredient> ingredientList, List<String> recipeInstructions) {
        //void
    }

    //EFFECTS: returns recipes which can be cooked with ingredients available
    public List<Recipe> getCookableRecipes() {
        return null;
    }

}
