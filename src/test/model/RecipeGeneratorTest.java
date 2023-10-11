package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeGeneratorTest {
    RecipeGenerator generator;
    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;
    Recipe recipe1;
    Recipe recipe2;
    Recipe recipe3;


    @BeforeEach
    void setUp() {
        generator = new RecipeGenerator();
        ingredient1 = new Ingredient("ingredient1",0.0);
        ingredient2 = new Ingredient("ingredient2",0.0);
        ingredient3 = new Ingredient("ingredient3",0.0);
        recipe1 = new Recipe("recipe1");
        recipe2 = new Recipe("recipe1");
        recipe3 = new Recipe("recipe1");
        recipeSetup();
    }

    @Test
    void testCreateIngredientUnique() {
        generator.createIngredient("ingredient1");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);

        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testCreateIngredientDuplicate() {
        generator.createIngredient("ingredient1");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        assertEquals(ingredientList,generator.getAvailableIngredients());

        generator.createIngredient("ingredient1");
        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testCreateIngredientDuplicateWithMultipleIngredients() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientList.add(ingredient3);


        generator.createIngredient("ingredient3");
        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testAddQuantityToIngredientWithNoIngredients() {
        assertFalse(generator.addQuantityToIngredient("ingredient1",10.0));
    }

    @Test
    void testAddQuantityToIngredientWithOneCorrectIngredients() {
        generator.createIngredient("ingredient1");

        assertTrue(generator.addQuantityToIngredient("ingredient1",10.0));

        ingredient1.addIngredient(10.0);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);

        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testAddQuantityToIngredientWithOneIncorrectIngredients() {
        generator.createIngredient("ingredient1");

        assertFalse(generator.addQuantityToIngredient("ingredient2",10.0));

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);

        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testAddQuantityToIngredientWithOneCorrectIngredientAndMultipleIngredients() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        ingredient3.addIngredient(10);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);



        assertTrue(generator.addQuantityToIngredient("ingredient3",10.0));
        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testAddQuantityToIngredientWithNoCorrectIngredientAndMultipleIngredients() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientList.add(ingredient3);

        assertFalse(generator.addQuantityToIngredient("ingredient4",10.0));
        assertEquals(ingredientList,generator.getAvailableIngredients());
    }

    @Test
    void testAddRecipe() {
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe1);

        generator.addRecipe(recipe1);
        assertEquals(recipeArrayList,generator.getRecipeList());

        recipeArrayList.add(recipe1);
        generator.addRecipe(recipe1);
        assertEquals(recipeArrayList,generator.getRecipeList());

    }

    @Test
    void testGetCookableRecipesNoRecipeReturned() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        generator.addQuantityToIngredient("ingredient1",1.0);
        generator.addQuantityToIngredient("ingredient2",1.0);
        generator.addQuantityToIngredient("ingredient3",1.0);

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();

        assertEquals(recipeArrayList,generator.getCookableRecipes());
    }

    @Test
    void testGetCookableRecipesOneRecipeReturned() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        generator.addQuantityToIngredient("ingredient1",9.0);
        generator.addQuantityToIngredient("ingredient2",10.0);
        generator.addQuantityToIngredient("ingredient3",10.0);

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe1);

        assertEquals(recipeArrayList,generator.getCookableRecipes());
    }

    @Test
    void testGetCookableRecipesTwoRecipeReturnedEquals() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        generator.addQuantityToIngredient("ingredient1",10.0);
        generator.addQuantityToIngredient("ingredient2",10.0);
        generator.addQuantityToIngredient("ingredient3",10.0);

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe1);
        recipeArrayList.add(recipe2);

        assertEquals(recipeArrayList,generator.getCookableRecipes());
    }

    @Test
    void testGetCookableRecipesThreeRecipeReturned() {
        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

        generator.addQuantityToIngredient("ingredient1",30.0);
        generator.addQuantityToIngredient("ingredient2",30.0);
        generator.addQuantityToIngredient("ingredient3",30.0);

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        recipeArrayList.add(recipe1);
        recipeArrayList.add(recipe2);
        recipeArrayList.add(recipe3);

        assertEquals(recipeArrayList,generator.getCookableRecipes());
    }

    //EFFECTS: creates 3 recipes with varying amounts of ingredient quantities needed
    private void recipeSetup() {
        Ingredient recipeIngredient1 = new Ingredient("ingredient1",5.0);
        Ingredient recipeIngredient2 = new Ingredient("ingredient2",10.0);
        Ingredient recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe1.addIngredient(recipeIngredient1);
        recipe1.addIngredient(recipeIngredient2);
        recipe1.addIngredient(recipeIngredient3);
        recipe1.addRecipeStep("Step 1");
        recipe1.addRecipeStep("Step 2");

        recipeIngredient1 = new Ingredient("ingredient1",10.0);
        recipeIngredient2 = new Ingredient("ingredient2",10.0);
        recipeIngredient3 = new Ingredient("ingredient3",10.0);
        recipe2.addIngredient(recipeIngredient1);
        recipe2.addIngredient(recipeIngredient2);
        recipe2.addIngredient(recipeIngredient3);
        recipe2.addRecipeStep("Step 1");
        recipe2.addRecipeStep("Step 2");

        recipeIngredient1 = new Ingredient("ingredient1",20.0);
        recipeIngredient2 = new Ingredient("ingredient2",20.0);
        recipeIngredient3 = new Ingredient("ingredient3",20.0);
        recipe3.addIngredient(recipeIngredient1);
        recipe3.addIngredient(recipeIngredient2);
        recipe3.addIngredient(recipeIngredient3);
        recipe3.addRecipeStep("Step 1");
        recipe3.addRecipeStep("Step 2");
    }
}