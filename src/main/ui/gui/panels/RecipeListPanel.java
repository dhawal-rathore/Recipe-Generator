package ui.gui.panels;

import model.Ingredient;
import model.Recipe;
import ui.gui.MainFrame;
import ui.gui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//represents a panel which shows a list of recipes which is scrollable
public class RecipeListPanel extends JPanel {
    private MainFrame originFrame;

    public RecipeListPanel(List<Recipe> recipeList, MainFrame originFrame) {
        super();
        setLayout(new BorderLayout());

        this.originFrame = originFrame;

        RecipeButtons buttonPane = new RecipeButtons(recipeList);
        JScrollPane scrollPane = new JScrollPane(buttonPane);
        scrollPane.setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        add(scrollPane,BorderLayout.CENTER);
        setName("Recipe List Panel");

    }

    //represents a panel of a list of ingredients which is not scrollable
    private class RecipeButtons extends JPanel {

        public RecipeButtons(List<Recipe> recipeList) {
            super();
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            setBackground(Color.WHITE);
            setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
            ButtonListener listener = new ButtonListener(originFrame);
            for (JButton button : getButtons(recipeList,listener)) {
                add(button);
            }

        }

        //EFFECTS: creates a list of buttons, initialises it for the list of recipes and returns the list
        private List<JButton> getButtons(List<Recipe> recipeList, ActionListener listener) {
            ArrayList<JButton> buttonList = new ArrayList<>();
            for (Recipe recipe : recipeList) {

                JButton button = new JButton(recipe.getName());
                button.setMinimumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setMaximumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(listener);
                button.setFont(MainFrame.FONT);
                button.putClientProperty("recipe",recipe);
                button.setActionCommand("changeToRecipePanel");

                buttonList.add(button);

            }
            return buttonList;
        }
    }

}
