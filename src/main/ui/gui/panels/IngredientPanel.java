package ui.gui.panels;

import model.Ingredient;
import ui.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class IngredientPanel extends JPanel {

    public IngredientPanel(Ingredient ingredient) {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        setPreferredSize(new Dimension(MainFrame.WIDTH,MainFrame.HEIGHT));
        setName("Ingredient Panel");

        JLabel name = createNameLabel(ingredient);
        JLabel quantity = createQuantityLabel(ingredient);

        add(name,BorderLayout.CENTER);
        add(quantity,BorderLayout.PAGE_END);

    }

    private JLabel createNameLabel(Ingredient ingredient) {
        JLabel name = new JLabel(String.format("Name : %s",ingredient.getName()),SwingConstants.CENTER);
        return buttonSetup(name);
    }

    private JLabel createQuantityLabel(Ingredient ingredient) {
        JLabel quantity = new JLabel(String.format("Quantity: %.1f",ingredient.getQuantity()),SwingConstants.CENTER);
        return buttonSetup(quantity);
    }

    private JLabel buttonSetup(JLabel label) {
        label.setMinimumSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 2));
        label.setPreferredSize(new Dimension(MainFrame.WIDTH / 2,MainFrame.HEIGHT / 2));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setFont(MainFrame.FONT);
        return label;
    }

}
