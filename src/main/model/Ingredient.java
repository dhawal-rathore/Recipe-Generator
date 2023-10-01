package model;

// Represents an Ingredient with name and quantity with optional description
public class Ingredient {

    public Ingredient(String name, double quantity) {
        //stub
    }

    //REQUIRES: Ingredient to have same name
    //EFFECTS: returns True if Ingredient has quantity >= to Ingredient that provided
    public Boolean hasQuantityMoreThan(Ingredient that) {
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

    //REQUIRES: 0 < addQuantity
    //MODIFIES: this
    //EFFECTS: increases quantity by addQuantity
    public void addIngredient(double addQuantity) {
        //stub
    }

    public String getName() {
        return null;
    }

    public double getQuantity() {
        return 0.0;
    }
}
