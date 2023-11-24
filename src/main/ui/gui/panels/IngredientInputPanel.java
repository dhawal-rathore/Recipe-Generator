package ui.gui.panels;

import ui.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents a panel which accepts input from the user to add ingredient
public class IngredientInputPanel extends JPanel {

    public IngredientInputPanel(MainFrame originalFrame) {
        setLayout(null);

        JTextField nameField = new JTextField();
        nameField.setBounds(70, 20, 200, 30);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(70, 60, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 40, 30);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 60, 60, 30);

        JButton submitButton = buttonSetup();
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double quantity = Double.parseDouble(quantityField.getText());
                originalFrame.addIngredient(name,quantity);
            }
        });

        add(nameField);
        add(nameLabel);
        add(quantityField);
        add(quantityLabel);
        add(submitButton);

    }

    //EFFECTS: sets up button
    private JButton buttonSetup() {
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 100, 200, 30);
        submitButton.setActionCommand("addAvailableIngredient");
        return submitButton;
    }
}
