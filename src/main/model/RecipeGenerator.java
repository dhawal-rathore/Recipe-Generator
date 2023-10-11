package model;

import java.util.ArrayList;
import java.util.List;

//represents the overlying class that the UI interacts with
public class RecipeGenerator {

    List<Ingredient> availableIngredients;
    List<Recipe> recipeList;

    public RecipeGenerator() {
        availableIngredients = new ArrayList<>();
        recipeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to available ingredients with quantity 0 if it isn't already in ingredients.
    public void createIngredient(String name) {
        boolean alreadyPresent = false;
        for (Ingredient availableIngredient : availableIngredients) {
            if (availableIngredient.getName().equals(name)) {
                alreadyPresent = true;
                break;
            }
        }
        if (!alreadyPresent) {
            Ingredient ingredient = new Ingredient(name,0.0);
            availableIngredients.add(ingredient);
        }
    }

    //REQUIRES: quantity > 0
    //MODIFIES: this
    //EFFECTS: adds quantity to Ingredient quantity if ingredient with name in availableIngredients and returns true
    //         else returns false
    public Boolean addQuantityToIngredient(String name, double quantity) {
        for (Ingredient ingredient : availableIngredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                ingredient.addIngredient(quantity);
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: adds recipe to recipeList
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }

    //EFFECTS: returns recipes which can be cooked with ingredients available
    public List<Recipe> getCookableRecipes() {
        List<Recipe> cookableRecipe = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.canRecipeBeMade(availableIngredients)) {
                cookableRecipe.add(recipe);
            }
        }
        return cookableRecipe;
    }

    public List<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
