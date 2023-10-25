package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;
    Ingredient ingredient4;
    Ingredient ingredient5;


    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient("Ingredient1",10.0);
        ingredient2 = new Ingredient("Ingredient2", 2.0);
        ingredient3 = new Ingredient("Ingredient3",10.0);
        ingredient4 = new Ingredient("Ingredient1",20.0);
        ingredient5 = new Ingredient("Ingredient1",10.0);

    }

    @Test
    void testHasQuantityMoreThan() {
        assertTrue(ingredient1.hasQuantityMoreThan(ingredient2));
        assertTrue(ingredient1.hasQuantityMoreThan(ingredient3));
        assertFalse(ingredient1.hasQuantityMoreThan(ingredient4));

    }

    @Test
    void testAreNamesEqual() {
        assertFalse(ingredient1.equalNames(ingredient2));
        assertTrue(ingredient1.equalNames(ingredient4));
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

    @Test
    void testPrintable() {
        assertEquals("10.00 Ingredient1",ingredient1.printable());
    }

    @Test
    void testEquals() {
        assertTrue(ingredient1.equals(ingredient5));
        assertFalse(ingredient1.equals(ingredient4));
        assertFalse(ingredient1.equals(ingredient2));
        assertFalse(ingredient1.equals(ingredient3));
    }

    @Test
    void testToJson() {
        JSONObject ingredient1Json = new JSONObject();
        ingredient1Json.put("name",ingredient1.getName());
        ingredient1Json.put("quantity",ingredient1.getQuantity());
        assertTrue(ingredient1Json.similar(ingredient1.toJson()));
    }
}