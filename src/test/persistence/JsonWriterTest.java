package persistence;

import model.RecipeGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//Inspired by JSONSerializationDemo project
//github: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeGenerator generator = new RecipeGenerator();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeGenerator generator = new RecipeGenerator();
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyRecipeGenerator.json");
            writer.open();
            writer.write(generator);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyRecipeGenerator.json");
            RecipeGenerator jsonGenerator = reader.read();
            equalRecipeGenerator(jsonGenerator,generator);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        RecipeGenerator actualGenerator = generateNormalRecipeGenerator();

        try {
            JsonWriter writer = new JsonWriter("./data/testWriteRecipeGenerator.json");
            writer.open();
            writer.write(actualGenerator);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteRecipeGenerator.json");
            RecipeGenerator jsonGenerator = reader.read();
            equalRecipeGenerator(jsonGenerator, actualGenerator);
        } catch (IOException e) {
            fail();
        }
    }
}