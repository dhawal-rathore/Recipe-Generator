package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a Recipe as a collection of Ingredients with
public class Recipe implements Writable {

    private final String name;
    private final List<Ingredient> ingredientList;
    private final List<String> recipeInstructions;


    public Recipe(String name) {
        this.name = name;
        this.ingredientList = new ArrayList<>();
        this.recipeInstructions = new ArrayList<>();
    }

    public Recipe(String name, List<Ingredient> ingredientList, List<String> recipeInstructions) {
        this.name = name;
        this.ingredientList = ingredientList;
        this.recipeInstructions = recipeInstructions;
    }

    //MODIFIES: this
    //EFFECTS: adds Ingredient to ingredientList
    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    //MODIFIES: this
    //EFFECTS: adds recipe step to recipeInstructions
    public void addRecipeStep(String recipeStep) {
        recipeInstructions.add(recipeStep);
    }

    //EFFECTS: checks if available ingredients are sufficient to cook
    public boolean canRecipeBeMade(List<Ingredient> availableIngredients) {
        boolean b = true;
        for (Ingredient recipeIngredient : ingredientList) {
            int ingredientIndex = getIndexOfIngredient(recipeIngredient,availableIngredients);
            if (ingredientIndex != -1) {
                if (availableIngredients.get(ingredientIndex).hasQuantityMoreThan(recipeIngredient)) {
                    continue;
                } else {
                    b = false;
                    break;
                }
            } else {
                b = false;
                break;
            }
        }
        return b;
    }

    //EFFECTS: returns index of ingredient if in list, else returns 0
    private int getIndexOfIngredient(Ingredient recipeIngredient, List<Ingredient> availableIngredients) {
        for (int i = 0; i < availableIngredients.size(); i++) {
            if (availableIngredients.get(i).equalNames(recipeIngredient)) {
                return i;
            }
        }
        return -1;
    }

    //EFFECTS: returns formatted String to print recipe
    public String recipePrintable() {
        String recipeString = "";
        recipeString += String.format("Name: %s\n",name);
        recipeString += "Ingredients:\n\n";
        for (Ingredient ingredient : ingredientList) {
            recipeString += String.format("%.1f %s\n",ingredient.getQuantity(),ingredient.getName());
        }
        recipeString += "\nSteps:\n";
        for (int i = 0; i < recipeInstructions.size(); i++) {
            recipeString += String.format("\n%d. %s", i + 1,recipeInstructions.get(i));
        }
        return recipeString;
    }

    //EFFECTS: returns true if both recipes have same ingredient lists
    public Boolean equalIngredientsAndName(Recipe that) {
        Boolean b = true;
        if (this.ingredientList.size() == that.ingredientList.size() && this.ingredientList.size() > 0) {
            for (int i = 0; i < ingredientList.size(); i++) {
                if (this.ingredientList.get(i).equals(that.ingredientList.get(i))) {
                    continue;
                } else {
                    b = false;
                    break;
                }
            }
        } else {
            b = false;
        }

        return (this.name.equals(that.name)) && b;
    }

    //EFFECTS: returns Recipe as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("ingredients",ingredientsToJson());
        json.put("instructions",stepsToJson());
        return json;
    }

    //EFFECTS: returns ingredients as a JSON Array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Ingredient ingredient : ingredientList) {
            jsonArray.put(ingredient.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns recipeInstructions as a JSON Array
    private JSONArray stepsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String step : recipeInstructions) {
            JSONObject json = new JSONObject();
            json.put("step",step);
            jsonArray.put(json);
        }
        return jsonArray;
    }

    //EFFECTS: returns recipe as a string which can be parsed as html
    public String toHtml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<body style=\"margin:15;padding:0\">");
        builder.append(String.format("<p style=\"font-size:30px\">%s</p><br>",name));
        builder.append("<p style= \"font-size:25px\">Ingredients:</p>");
        builder.append("<ul>");
        for (Ingredient ingredient : ingredientList) {
            builder.append(String.format("<li>%.1f %s</li>",ingredient.getQuantity(),ingredient.getName()));
        }
        builder.append("</ul>");
        builder.append("<p style=\"font-size:25px\">Steps:</p>");
        builder.append("<ol>");
        for (int i = 0; i < recipeInstructions.size(); i++) {
            builder.append(String.format("<li>%s</li>",recipeInstructions.get(i)));
        }
        builder.append("</ol>");
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<String> getRecipeInstructions() {
        return recipeInstructions;
    }

}
