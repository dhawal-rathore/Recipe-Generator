package ui.gui.listeners;

import model.Ingredient;
import model.Recipe;
import ui.gui.MainFrame;
import ui.gui.panels.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//implements actionListener for all classes
public class ButtonListener implements ActionListener {
    private MainFrame originalFrame;

    public ButtonListener(MainFrame originalFrame) {
        this.originalFrame = originalFrame;
    }

    //MODIFIES: originalFrame
    //EFFECTS: reads actionCommand and runs appropriate method
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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
            case "changeToAddIngredientPanel":
                changeToAddIngredientPanel();
                break;
            case "previous":
                changeToPreviousPanel();
                break;
            case "forward":
                changeToNextPanel();
                break;
            default:
                System.err.println("unrecognized button");
                break;
        }

    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to an ingredient panel specified in button
    private void changeToIngredientPanel(ActionEvent e) {
        Ingredient ingredient = (Ingredient) ((JButton)e.getSource()).getClientProperty("ingredient");
        originalFrame.switchPanelTo(new IngredientPanel(ingredient));
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to an ingredient list panel specified in button
    private void changeToIngredientListPanel(ActionEvent e) {
        List<Ingredient> ingredientList;
        ingredientList = (List<Ingredient>) ((JButton)e.getSource()).getClientProperty("ingredientList");
        originalFrame.switchPanelTo(new IngredientListPanel(ingredientList,originalFrame));
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to an recipe panel specified in button
    private void changeToRecipePanel(ActionEvent e) {
        Recipe recipe = (Recipe) ((JButton)e.getSource()).getClientProperty("recipe");
        originalFrame.switchPanelTo(new RecipePanel(recipe));
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to an recipe list panel specified in button
    private void changeToRecipeListPanel(ActionEvent e) {
        List<Recipe> recipeList;
        recipeList = (List<Recipe>) ((JButton)e.getSource()).getClientProperty("recipeList");
        System.out.println(recipeList.size());
        originalFrame.switchPanelTo(new RecipeListPanel(recipeList,originalFrame));
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to add ingredient to available ingredients panel specified in button
    private void changeToAddIngredientPanel() {
        originalFrame.switchPanelTo(new IngredientInputPanel(originalFrame));
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to previous panel
    private void changeToPreviousPanel() {
        originalFrame.previousPanel();
    }

    //MODIFIES: originalFrame
    //EFFECTS: switches panel to next panel if it exists
    private void changeToNextPanel() {
        originalFrame.nextPanel();
    }

}
