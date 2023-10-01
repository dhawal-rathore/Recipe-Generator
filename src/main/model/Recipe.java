package model;

import java.util.List;

//represents a Recipe as a collection of Ingredients with
public class Recipe {

    public Recipe(String name) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to listOfIngredients
    public void addIngredient(Ingredient ingredient){
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
}
