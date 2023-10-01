package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;
    Ingredient ingredient4;


    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient("Ingredient1",10.0);
        ingredient2 = new Ingredient("Ingredient2", 2.0);
        ingredient3 = new Ingredient("Ingredient3",10.0);
        ingredient4 = new Ingredient("Ingredient1",20.0);

    }

    @Test
    void testHasQuantityMoreThan() {
        assertTrue(ingredient1.hasQuantityMoreThan(ingredient2));
        assertTrue(ingredient1.hasQuantityMoreThan(ingredient3));
        assertFalse(ingredient1.hasQuantityMoreThan(ingredient4));

    }

    @Test
    void testAreNamesEqual() {
        assertFalse(ingredient1.areNamesEqual(ingredient2));
        assertTrue(ingredient1.areNamesEqual(ingredient4));
    }

    @Test
    void testUseIngredient() {
        ingredient1.useIngredient(2.0);
        assertEquals(8.0,ingredient1.getQuantity());
    }

    @Test
    void testAddIngredient() {
        ingredient1.addIngredient(2.0);
        assertEquals(12.0,ingredient1.getQuantity());
    }

}