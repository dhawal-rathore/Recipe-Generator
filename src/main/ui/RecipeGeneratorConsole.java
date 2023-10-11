package ui;

import model.Ingredient;
import model.RecipeGenerator;

import java.util.ArrayList;

//represents the console seen by the user
public class RecipeGeneratorConsole {

    //EFFECTS: accepts choice from user and displays functionality related to the choice
    public Boolean mainInterface() {
        return false;
    }

    //EFFECTS: displays functionality related to the choice
    public void choice(int n) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: Accepts Ingredient from user and adds it to availableIngredients with quantity 0
    private void addIngredientToAvailableIngredients() {
        //void
    }

    //MODIFIES: this
    //EFFECTS: Accepts name from user and adds quantity to ingredient with name if in list
    private void addQuantityToIngredient() {
        //stub
    }

    //EFFECTS: shows all available ingredients with quantity
    private void showIngredients(){
        //stub
    }

    //MODIFIES: this
    //EFFECTS: gets user to input name, ingredients(have to be in availableIngredients), and steps
    //         and adds recipe to RecipeList
    private void createRecipe(){
        //stub
    }

    //MODIFIES: this
    //EFFECTS: allows user to view recipe with name given by user
    private void viewRecipe(){
        //stub
    }

    //EFFECTS: allows user to view recipe that can be cooked with ingredients with enough quantity available
    private void showCookableRecipe(){
        //stub
    }
}
