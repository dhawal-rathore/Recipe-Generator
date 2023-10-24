package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Inspired by JSONSerializationDemo project
//github: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {

    protected void equalRecipeGenerator(RecipeGenerator jsonGenerator, RecipeGenerator actualGenerator){
        equalIngredientList(jsonGenerator.getAvailableIngredients(),actualGenerator.getAvailableIngredients());
        equalRecipeList(jsonGenerator,actualGenerator);
    }

    private void equalIngredientList(List<Ingredient> jsonIngredientList, List<Ingredient> actualIngredientList) {
        if (jsonIngredientList.size() == actualIngredientList.size()) {
            for(int i = 0; i < jsonIngredientList.size(); i++) {
                assertTrue(jsonIngredientList.get(i).equals(actualIngredientList.get(i)));
            }
        } else{
            fail("Ingredient Lists should have same length");
        }
    }

    private void equalRecipeList(RecipeGenerator jsonGenerator, RecipeGenerator actualGenerator) {
        List<Recipe> jsonRecipeList = jsonGenerator.getRecipeList();
        List<Recipe> actualRecipeList = actualGenerator.getRecipeList();
        if (jsonRecipeList.size() == actualRecipeList.size()) {
            for (int i = 0; i < jsonRecipeList.size(); i++) {
                assertEquals(jsonRecipeList.get(i).getName(),actualRecipeList.get(i).getName());
                equalIngredientList(jsonRecipeList.get(i).getIngredientList(),actualRecipeList.get(i).getIngredientList());
                equalRecipeSteps(jsonRecipeList.get(i).getRecipeInstructions(),actualRecipeList.get(i).getRecipeInstructions());
            }
        } else {
            fail("Recipe List should have the same length.");
        }
    }

    private void equalRecipeSteps(List<String> jsonSteps, List<String> actualSteps) {
        if (jsonSteps.size() == actualSteps.size()) {
            for (int i = 0; i < jsonSteps.size(); i++) {
                assertEquals(jsonSteps.get(i),actualSteps.get(i));
            }
        } else {
            fail("Recipe Steps should be of equal length");
        }
    }

    protected RecipeGenerator generateNormalRecipeGenerator() {
        RecipeGenerator actualGenerator = new RecipeGenerator();
        Recipe recipe1 = new Recipe("Recipe1");
        Recipe recipe2 = new Recipe("Recipe2");


        actualGenerator.createIngredient("Ingredient1");
        actualGenerator.createIngredient("Ingredient2");
        actualGenerator.createIngredient("Ingredient3");
        actualGenerator.addQuantityToIngredient("Ingredient1", 10);
        actualGenerator.addQuantityToIngredient("Ingredient2", 15);

        Ingredient recipeIngredient1 = new Ingredient("Ingredient1", 5.0);
        Ingredient recipeIngredient2 = new Ingredient("Ingredient2", 3.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addRecipeStep("Recipe Step 1");
        recipe1.addRecipeStep("Recipe Step 2");
        recipe1.addRecipeStep("Recipe Step 3");

        recipeIngredient2 = new Ingredient("Ingredient2", 3.0);
        Ingredient recipeIngredient3 = new Ingredient("Ingredient3", 15.0);
        recipe2.addIngredient(recipeIngredient3);
        recipe2.addIngredient(recipeIngredient2);
        recipe2.addRecipeStep("Recipe Step 1");
        recipe2.addRecipeStep("Recipe Step 2");
        recipe2.addRecipeStep("Recipe Step 3");

        actualGenerator.addRecipe(recipe1);
        actualGenerator.addRecipe(recipe2);

        return actualGenerator;
    }

}
