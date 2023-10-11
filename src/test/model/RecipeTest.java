package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    Recipe recipe1;
    Recipe recipe2;
    Recipe recipe3;
    ArrayList<Ingredient> ingredientList;
    ArrayList<String> instructions;
    List<Ingredient> availableIngredients;


    @BeforeEach
    void setUp(){
        ingredientList = new ArrayList<>();
        instructions = new ArrayList<>();
        availableIngredients = new ArrayList<>();

        recipe1 = new Recipe("recipe1");
        recipe2 = new Recipe("recipe2", new ArrayList<Ingredient>(),new ArrayList<String>());
        recipe3 = new Recipe("recipe1");

    }

    @Test
    void testAddIngredient() {
        Ingredient ingredient = new Ingredient("ingredient",10.0);
        ingredientList.add(ingredient);

        recipe1.addIngredient(ingredient);
        recipe2.addIngredient(ingredient);
        assertEquals(ingredientList,recipe1.getIngredientList());
        assertEquals(ingredientList,recipe2.getIngredientList());
    }

    @Test
    void testAddRecipeStep() {
        String recipeStep1 = "Step 1";
        String recipeStep2 = "Step 2";
        instructions.add(recipeStep1);

        recipe1.addRecipeStep(recipeStep1);
        recipe2.addRecipeStep(recipeStep1);
        assertEquals(instructions,recipe1.getRecipeInstructions());
        assertEquals(instructions,recipe2.getRecipeInstructions());

        instructions.add(recipeStep2);
        recipe1.addRecipeStep(recipeStep2);
        recipe2.addRecipeStep(recipeStep2);
        assertEquals(instructions,recipe1.getRecipeInstructions());
        assertEquals(instructions,recipe2.getRecipeInstructions());
    }

    @Test
    void testCanRecipeBeMadeWithOneIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        availableIngredients.add(ingredient1);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        recipe1.addIngredient(recipeIngredient1);

        assertTrue(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithOneEqualQuantityIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        availableIngredients.add(ingredient1);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",10.0);
        recipe1.addIngredient(recipeIngredient1);

        assertTrue(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithOneInsufficientIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        availableIngredients.add(ingredient1);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",15.0);
        recipe1.addIngredient(recipeIngredient1);

        assertFalse(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithOneDifferentIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        availableIngredients.add(ingredient1);

        Ingredient recipeIngredient1 = new Ingredient("ingredient2",5.0);
        recipe1.addIngredient(recipeIngredient1);

        assertFalse(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithMultipleIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        Ingredient ingredient2 = new Ingredient("ingredient2",10.0);
        Ingredient ingredient3 = new Ingredient("ingredient3",10.0);
        availableIngredients.add(ingredient1);
        availableIngredients.add(ingredient2);
        availableIngredients.add(ingredient3);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);

        assertTrue(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithMultipleInsufficientIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        Ingredient ingredient2 = new Ingredient("ingredient2",10.0);
        Ingredient ingredient3 = new Ingredient("ingredient3",10.0);
        availableIngredients.add(ingredient1);
        availableIngredients.add(ingredient2);
        availableIngredients.add(ingredient3);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",10.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",15.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);

        assertFalse(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testCanRecipeBeMadeWithMultipleDifferentIngredient() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        Ingredient ingredient2 = new Ingredient("ingredient2",10.0);
        Ingredient ingredient3 = new Ingredient("ingredient3",10.0);
        availableIngredients.add(ingredient1);
        availableIngredients.add(ingredient2);
        availableIngredients.add(ingredient3);

        Ingredient recipeIngredient1 = new Ingredient("ingredient1",10.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient4",10.0); // different name
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);

        assertFalse(recipe1.canRecipeBeMade(availableIngredients));
    }

    @Test
    void testRecipePrintable() {
        Ingredient ingredient1 = new Ingredient("ingredient1",10.0);
        Ingredient ingredient2 = new Ingredient("ingredient2",5.5);
        recipe1.addIngredient(ingredient1);
        recipe1.addIngredient(ingredient2);
        recipe1.addRecipeStep("Step 1");
        recipe1.addRecipeStep("Step 2");

        String recipeString = String.join("\n",
                "Name: recipe1",
                "Ingredients:",
                "",
                "10.0 ingredient1",
                "5.5 ingredient2",
                "",
                "Steps:",
                "",
                "1. Step 1",
                "2. Step 2");

        assertEquals(recipeString,recipe1.recipePrintable());
    }

    @Test
    void testEqualIngredientsWhenEqual() {
        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);
        recipe3.addIngredient(recipeIngredient1);
        recipe3.addIngredient(recipeIngredient2);
        recipe3.addIngredient(recipeIngredient3);

        assertTrue(recipe1.equalIngredientsAndName(recipe3));
    }

    @Test
    void testEqualIngredientsWhenEqualDifferentName() {
        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);
        recipe2.addIngredient(recipeIngredient1);
        recipe2.addIngredient(recipeIngredient2);
        recipe2.addIngredient(recipeIngredient3);

        assertFalse(recipe1.equalIngredientsAndName(recipe2));
    }

    @Test
    void testEqualIngredientsWhenEqualNoIngredients() {
        assertFalse(recipe1.equalIngredientsAndName(recipe3));
    }


    @Test
    void testEqualIngredientsWhenEqualSizeDifferentIngredient() {
        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe3.addIngredient(recipeIngredient1);
        recipe3.addIngredient(recipeIngredient3);

        assertFalse(recipe1.equalIngredientsAndName(recipe3));
    }

    @Test
    void testEqualIngredientsWhenDifferentSizeDifferentIngredient() {
        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe3.addIngredient(recipeIngredient1);
        recipe3.addIngredient(recipeIngredient3);

        assertFalse(recipe1.equalIngredientsAndName(recipe3));
    }

    @Test
    void testGetName() {
        assertEquals("recipe1",recipe1.getName());
    }
}