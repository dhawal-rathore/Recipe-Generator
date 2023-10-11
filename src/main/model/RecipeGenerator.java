package model;

import java.util.List;

//represents the overlying class that the UI interacts with
public class RecipeGenerator {

    public RecipeGenerator() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to available ingredients with quantity 0 if it isn't already in ingredients.
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
    public void addRecipe(Recipe recipe) {
        //void
    }

    //EFFECTS: returns recipes which can be cooked with ingredients available
    public List<Recipe> getCookableRecipes() {
        return null;
    }

    public List<Ingredient> getAvailableIngredients() {
        return null;
    }

    public List<Recipe> getRecipeList() {
        return null;
    }
}
