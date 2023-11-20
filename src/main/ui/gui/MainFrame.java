package ui.gui;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import ui.gui.panels.MainPanel;
import ui.gui.panels.RecipePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final int HEIGHT = 300 * 3;
    public static final int WIDTH = 400 * 3;
    public static final Font FONT = new Font("Helvetica", Font.PLAIN, 44);

    public MainFrame() {
        super("Main Window");
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

        RecipeGenerator generator = new RecipeGenerator();
        recipeGeneratorSetup(generator);
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

