package model;

// Represents an Ingredient with name and quantity with optional description
public class Ingredient {

    //REQUIRES: Ingredient to have same name
    //EFFECTS: returns True if Ingredient has quantity >= to Ingredient provided
    public Boolean hasQuantityMoreThanRequired(Ingredient that) {
        return false;
    }

    //EFFECTS: returns True if Ingredients have the same name
    public Boolean areNamesEqual(Ingredient that) {
        return false;
    }

    //REQUIRES: 0 < useQuantity < this.quantity;
    //MODIFIES: this
    //EFFECTS: reduces quantity by useQuantity
    public void useIngredient(double useQuantity) {
        //stub
    }

}
