package ui.gui.listeners;

import model.Ingredient;
import model.Recipe;
import ui.gui.MainFrame;
import ui.gui.panels.IngredientListPanel;
import ui.gui.panels.IngredientPanel;
import ui.gui.panels.RecipeListPanel;
import ui.gui.panels.RecipePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListener implements ActionListener {
    private MainFrame originalFrame;

    public ButtonListener(MainFrame originalFrame) {
        this.originalFrame = originalFrame;
    }


    @SuppressWarnings("checkstyle:MethodLength")
    public void actionPerformed(ActionEvent e) {
        String actionCommand = ((JButton)e.getSource()).getActionCommand();
        switch (actionCommand) {
            case "changeToIngredientPanel":
                changeToIngredientPanel(e);
                break;
            case "changeToIngredientListPanel":
                changeToIngredientListPanel(e);
                break;
            case "changeToRecipePanel":
                changeToRecipePanel(e);
                break;
            case "changeToRecipeListPanel":
                changeToRecipeListPanel(e);
                break;
            case "previous":
                changeToPreviousPanel();
                break;
            case "forward":
                changeToNextPanel();
                break;
            default:
                System.out.println("ok");
                System.out.println(actionCommand);
                break;
        }

    }

    private void changeToIngredientPanel(ActionEvent e) {
        Ingredient ingredient = (Ingredient) ((JButton)e.getSource()).getClientProperty("ingredient");
        originalFrame.switchPanelTo(new IngredientPanel(ingredient));
    }

    private void changeToIngredientListPanel(ActionEvent e) {
        List<Ingredient> ingredientList;
        ingredientList = (List<Ingredient>) ((JButton)e.getSource()).getClientProperty("ingredientList");
        originalFrame.switchPanelTo(new IngredientListPanel(ingredientList,originalFrame));
    }

    private void changeToRecipePanel(ActionEvent e) {
        Recipe recipe = (Recipe) ((JButton)e.getSource()).getClientProperty("recipe");
        originalFrame.switchPanelTo(new RecipePanel(recipe));
    }

    private void changeToRecipeListPanel(ActionEvent e) {
        List<Recipe> recipeList;
        recipeList = (List<Recipe>) ((JButton)e.getSource()).getClientProperty("recipeList");
        originalFrame.switchPanelTo(new RecipeListPanel(recipeList,originalFrame));
    }

    private void changeToPreviousPanel() {
        originalFrame.previousPanel();
    }

    private void changeToNextPanel() {
        originalFrame.nextPanel();
    }

}
