package ui.gui.panels;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import ui.gui.MainFrame;
import ui.gui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

//represents the home screen of the application
public class MainPanel extends JPanel {
    private ActionListener listener;
    private RecipeGenerator generator;

    public MainPanel(RecipeGenerator generator, MainFrame originFrame) {
        super();
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));

        listener = new ButtonListener(originFrame);
        this.generator = generator;

        JButton recipesButton = createRecipesButton();
        JButton ingredientButton = createAvailableIngredientButton();
        JButton addIngredientButton =  createAddToAvailableIngredientsButton();
        JButton showPreparableRecipesButton = createPreparableRecipesButton();
        JLabel banner = createBanner();

        add(banner,addConstraints(1,0,3,1));
        add(ingredientButton,addConstraints(1,2));
        add(recipesButton,addConstraints(1,3));
        add(addIngredientButton,addConstraints(1,4));
        add(showPreparableRecipesButton,addConstraints(1,5));
    }

    //EFFECTS: returns the label representing the title in the main screen
    private JLabel createBanner() {
        JLabel banner = new JLabel("Recipe Generator",SwingConstants.CENTER);
        banner.setMinimumSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 3));
        banner.setPreferredSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 3));
        banner.setBorder(BorderFactory.createLineBorder(Color.black));
        banner.setFont(MainFrame.FONT);
        return banner;
    }

    //EFFECTS: creates a button to access all recipes
    private JButton createRecipesButton() {
        JButton recipesButton = new JButton("Recipe Book");
        recipesButton.setActionCommand("changeToRecipeListPanel");

        List<Recipe> recipeList = generator.getRecipeList();
        recipesButton.putClientProperty("recipeList",recipeList);

        return buttonSetup(recipesButton);
    }

    //EFFECTS: creates a button to access all available ingredients
    private JButton createAvailableIngredientButton() {
        JButton ingredientsButton = new JButton("Available Ingredients");
        ingredientsButton.setActionCommand("changeToIngredientListPanel");

        List<Ingredient> ingredientList = generator.getAvailableIngredients();
        ingredientsButton.putClientProperty("ingredientList",ingredientList);

        return buttonSetup(ingredientsButton);
    }

    //EFFECTS: creates a button to add ingredient to available ingredients
    private JButton createAddToAvailableIngredientsButton() {
        JButton addIngredientsButton = new JButton("Add to Available Ingredients");
        addIngredientsButton.setActionCommand("changeToAddIngredientPanel");

        return buttonSetup(addIngredientsButton);
    }

    //EFFECTS: creates a button to show recipes which can be prepared by user
    private JButton createPreparableRecipesButton() {
        JButton showPreparableRecipesButton = new JButton("Show Preparable Recipes");
        showPreparableRecipesButton.setActionCommand("changeToRecipeListPanel");

        List<Recipe> recipeList = generator.getCookableRecipes();
        showPreparableRecipesButton.putClientProperty("recipeList",recipeList);

        return buttonSetup(showPreparableRecipesButton);
    }

    //EFFECTS: adds common functionality to a button and returns it
    private JButton buttonSetup(JButton button) {
        button.setMinimumSize(new Dimension(MainFrame.WIDTH / 3,MainFrame.HEIGHT / 3));
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.setFont(MainFrame.FONT);
        button.addActionListener(listener);
        return button;
    }

    //EFFECTS: returns a constraints object with x and y representing cells in a grid
    private GridBagConstraints addConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0.5;
        return c;
    }

    //EFFECTS: returns a constraints object
    private GridBagConstraints addConstraints(int x, int y,int gridwidth, int gridheight) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NORTH;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 1;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        return c;
    }
}
