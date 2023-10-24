package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//Inspired by JSONSerializationDemo project
//github: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest{

    @Test
    void testReadNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeGenerator generator = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadEmptyRecipeGenerator() {
        JsonReader reader = new JsonReader("./data/testReadEmptyRecipeGenerator.json");
        try {
            RecipeGenerator jsonGenerator = reader.read();
            equalRecipeGenerator(jsonGenerator, new RecipeGenerator());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testReadNormalRecipeGenerator() {
        JsonReader reader = new JsonReader("./data/testReadRecipeGenerator.json");
        RecipeGenerator actualGenerator = generateNormalRecipeGenerator();

        try {
            RecipeGenerator jsonGenerator = reader.read();
            equalRecipeGenerator(jsonGenerator, actualGenerator);
        } catch (IOException e) {
            fail();
        }

    }
}