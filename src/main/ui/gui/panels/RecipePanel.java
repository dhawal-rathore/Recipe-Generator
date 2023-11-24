package ui.gui.panels;

import model.Recipe;
import ui.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

//represents a panel which represents a recipe and is scrollable
public class RecipePanel extends JPanel {

    public RecipePanel(Recipe recipe) {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        setName("Recipe Panel");

        JScrollPane scrollPane = new JScrollPane(fullRecipePanel(recipe));
        scrollPane.setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        add(scrollPane,BorderLayout.CENTER);
        setName("Recipe Panel");

    }

    //EFFECTS: returns a panel containing the recipe
    private static JPanel fullRecipePanel(Recipe recipe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        panel.add(recipeLabel(recipe));

        return panel;
    }

    //EFFECTS: returns a label containing the recipe
    private static JLabel recipeLabel(Recipe recipe) {
        JLabel recipeLabel = new JLabel();
        recipeLabel.setText(recipe.toHtml());
        recipeLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
//        recipeLabel.setFont(MainFrame.FONT);
        recipeLabel.setMinimumSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 2));
        recipeLabel.setPreferredSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 2));
        recipeLabel.setBorder(BorderFactory.createEmptyBorder());

        return recipeLabel;
    }

}
