package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Ingredient with name and quantity
public class Ingredient implements Writable {
    private final String name;
    private Double quantity;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    //REQUIRES: Ingredient to have same name
    //EFFECTS: returns True if Ingredient has quantity >= to Ingredient that provided
    public Boolean hasQuantityMoreThan(Ingredient that) {
        return (this.quantity.compareTo(that.quantity) == 1) || (this.quantity.equals(that.quantity));
    }

    //EFFECTS: returns True if Ingredients have the same name
    public Boolean equals(Ingredient that) {
        return this.name.equals(that.name) && this.quantity.equals(that.quantity);
    }

    //REQUIRES: 0 < useQuantity < this.quantity;
    //MODIFIES: this
    //EFFECTS: reduces quantity by useQuantity
    public void useIngredient(double useQuantity) {
        quantity -= useQuantity;
    }

    //REQUIRES: 0 < addQuantity
    //MODIFIES: this
    //EFFECTS: increases quantity by addQuantity
    public void addIngredient(double addQuantity) {
        quantity += addQuantity;
    }

    //EFFECTS: returns True if Ingredients have the same name
    public Boolean equalNames(Ingredient that) {
        return this.name.equalsIgnoreCase(that.name);
    }

    //EFFECTS : returns ingredient as JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("quantity",quantity);
        return json;
    }

    //EFFECTS: returns String with name and quantity of ingredient
    public String printable() {
        return String.format("%.2f %s",quantity,name);
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }
}
