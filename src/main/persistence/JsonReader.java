package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//Inspired by JSONSerializationDemo project
//github: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeGenerator read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeGenerator(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private RecipeGenerator parseRecipeGenerator(JSONObject jsonObject) {
        RecipeGenerator generator = new RecipeGenerator();
        addIngredients(generator, jsonObject);
        addRecipeList(generator, jsonObject);
        return generator;
    }

    // MODIFIES: generator
    // EFFECTS: parses recipeList from JSON object and adds them to generator
    private void addRecipeList(RecipeGenerator generator, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(generator, nextRecipe);
        }
    }

    // MODIFIES: generator
    // EFFECTS: parses recipe from JSON object and adds it to generator
    private void addRecipe(RecipeGenerator generator, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ArrayList<Ingredient> ingredientList = parseIngredientList(jsonObject);
        ArrayList<String> recipeSteps = parseRecipeSteps(jsonObject);
        Recipe recipe = new Recipe(name, ingredientList,recipeSteps);
        generator.addRecipe(recipe);
    }

    // EFFECTS: parses list of ingredients from JSON Object and returns it
    private ArrayList<Ingredient> parseIngredientList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients");
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(ingredientArrayList, nextIngredient);
        }
        return ingredientArrayList;
    }

    //EFFECTS: parses recipe steps from json object and returns it as a arrayList
    private ArrayList<String> parseRecipeSteps(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("instructions");
        ArrayList<String> recipeInstructions = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextStep = (JSONObject) json;
            String step = nextStep.getString("step");
            recipeInstructions.add(step);
        }
        return recipeInstructions;
    }

    // MODIFIES: generator
    // EFFECTS: parses availableIngredients from JSON object and adds them to generator
    private void addIngredients(RecipeGenerator generator, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(generator, nextIngredient);
        }
    }

    // MODIFIES: generator
    // EFFECTS: parses ingredient from json object and adds it to generator with quantity
    private void addIngredient(RecipeGenerator generator, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double quantity = jsonObject.getDouble("quantity");
        generator.createIngredient(name);
        generator.addQuantityToIngredient(name,quantity);
    }

    // MODIFIES: ingredientArrayList
    // EFFECTS: parses ingredient from json object and adds it to arrayList
    private void addIngredient(ArrayList<Ingredient> ingredientArrayList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double quantity = jsonObject.getDouble("quantity");
        Ingredient ingredient = new Ingredient(name, quantity);
        ingredientArrayList.add(ingredient);
    }

}

