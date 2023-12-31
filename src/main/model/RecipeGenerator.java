package model;

import model.log.Event;
import model.log.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents the overlying class that the UI interacts with
public class RecipeGenerator implements Writable {

    private final List<Ingredient> availableIngredients;
    private final List<Recipe> recipeList;

    public RecipeGenerator() {
        availableIngredients = new ArrayList<>();
        recipeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to available ingredients with quantity 0 if it isn't already in ingredients.
    public void createIngredient(String name) {
        boolean alreadyPresent = false;
        for (Ingredient availableIngredient : availableIngredients) {
            if (availableIngredient.getName().equals(name.toLowerCase())) {
                alreadyPresent = true;
                break;
            }
        }
        if (!alreadyPresent) {
            Ingredient ingredient = new Ingredient(name.toLowerCase(),0.0);
            availableIngredients.add(ingredient);
            EventLog.getInstance().logEvent(new Event("Added ingredient to available ingredients"));
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
                EventLog.getInstance().logEvent(new Event("Added quantity to ingredient in available ingredients"));
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: adds recipe to recipeList
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added recipe to recipe list"));
    }

    //EFFECTS: returns recipes which can be cooked with ingredients available
    public List<Recipe> getCookableRecipes() {
        List<Recipe> cookableRecipe = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.canRecipeBeMade(availableIngredients)) {
                cookableRecipe.add(recipe);
            }
        }
        EventLog.getInstance().logEvent(new Event("Showing preparable recipes"));
        return cookableRecipe;
    }

    //EFFECTS: returns recipeGenerator as a JSON Array
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredients",ingredientsToJson());
        json.put("recipes",recipeToJson());
        return json;
    }

    //EFFECTS: returns ingredients as a JSON Array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ingredient ingredient : availableIngredients) {
            jsonArray.put(ingredient.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns ingredients as a JSON Array
    private JSONArray recipeToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Recipe recipe : recipeList) {
            jsonArray.put(recipe.toJson());
        }
        return jsonArray;
    }

    public List<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
