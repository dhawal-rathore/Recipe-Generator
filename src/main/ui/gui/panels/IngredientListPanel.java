package ui.gui.panels;

import model.Ingredient;
import ui.gui.MainFrame;
import ui.gui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IngredientListPanel extends JPanel {
    private MainFrame originFrame;

    public IngredientListPanel(List<Ingredient> ingredients, MainFrame originFrame) {
        super();
        setLayout(new BorderLayout());

        this.originFrame = originFrame;

        IngredientButtons buttonPane = new IngredientButtons(ingredients);
        JScrollPane scrollPane = new JScrollPane(buttonPane);
        scrollPane.setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        add(scrollPane,BorderLayout.CENTER);
        setName("Ingredient List Panel");

    }

    private class IngredientButtons extends JPanel {

        public IngredientButtons(List<Ingredient> ingredients) {
            super();
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            setBackground(Color.WHITE);
            setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
            ButtonListener listener = new ButtonListener(originFrame);
            for (JButton button : getButtons(ingredients,listener)) {
                add(button);
            }

        }

        private List<JButton> getButtons(List<Ingredient> ingredients, ActionListener listener) {
            ArrayList<JButton> buttonList = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {

                JButton button = new JButton(ingredient.printable());
                button.setMinimumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setMaximumSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT / 7));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(listener);
                button.setFont(MainFrame.FONT);
                button.putClientProperty("ingredient",ingredient);
                button.setActionCommand("changeToIngredientPanel");

                buttonList.add(button);

            }
            return buttonList;
        }
    }
}
