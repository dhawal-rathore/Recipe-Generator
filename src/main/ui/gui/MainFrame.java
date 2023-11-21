package ui.gui;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {
    public static final int HEIGHT = 300 * 3;
    public static final int WIDTH = 400 * 3;
    public static final Font FONT = new Font("Helvetica", Font.PLAIN, 44);
    private static final String FILE_LOCATION = "./data/RecipeGenerator.json";


    private RecipeGenerator generator;

    public MainFrame() {
        super("Main Window");
        frameSetup();
        askUserToLoad();
        askUserToSave();
//        recipeGeneratorSetup(generator);
        changePanel(new MainPanel(generator,this));
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
        pack();
        setVisible(true);
    }

    private void askUserToLoad() {
        int n = JOptionPane.showConfirmDialog(
                this,
                "Would you like to load saved file?",
                "Load File?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            loadGenerator();
        } else {
            generator = new RecipeGenerator();
        }
    }

    private void loadGenerator() {
        try {
            JsonReader reader = new JsonReader(FILE_LOCATION);
            generator = reader.read();
            System.out.println("Read from file successful.");
        } catch (IOException e) {
            System.out.println("Please save to file first and try again.");
        }
    }

    private void saveGenerator() {
        try {
            JsonWriter writer = new JsonWriter(FILE_LOCATION);
            writer.open();
            writer.write(generator);
            writer.close();
            System.out.println("Successfully written to file.");
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be written");
        }
    }

    private void frameSetup() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
    }

    private void askUserToSave() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you want to save to file ?", "Save Confirmation ",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    saveGenerator();
                    MainFrame.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    MainFrame.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void recipeGeneratorSetup(RecipeGenerator generator) {
        Recipe recipe1 = new Recipe("Recipe1");
        Recipe recipe2 = new Recipe("Recipe2");
        Recipe recipe3 = new Recipe("Recipe3");

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

        generator.addRecipe(recipe1);
        generator.addRecipe(recipe2);
        generator.addRecipe(recipe3);

        generator.createIngredient("ingredient1");
        generator.createIngredient("ingredient2");
        generator.createIngredient("ingredient3");

    }

}

