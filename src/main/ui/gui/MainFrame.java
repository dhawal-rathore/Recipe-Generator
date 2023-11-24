package ui.gui;

import model.Ingredient;
import model.Recipe;
import model.RecipeGenerator;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.listeners.ButtonListener;
import ui.gui.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

// represents the main window of the program
public class MainFrame extends JFrame {
    public static final int HEIGHT = 300 * 3;
    public static final int WIDTH = 400 * 3;
    public static final Font FONT = new Font("Helvetica", Font.PLAIN, 44);
    private static final String FILE_LOCATION = "./data/RecipeGenerator.json";
    private static final String ICON_DIR = "data/icons/";

    private RecipeGenerator generator;
    private LinkedList<JPanel> visitedPanels;
    private LinkedList<JPanel> forwardPanels;
    private ButtonListener listener;

    public MainFrame() {
        super("Main Window");
        frameSetup();
        askUserToLoad();
        askUserToSave();

        visitedPanels = new LinkedList<>();
        forwardPanels = new LinkedList<>();

        listener = new ButtonListener(this);

        switchPanelTo(new MainPanel(generator,this));
    }

    //MODIFIES: this
    //EFFECTS: switches panel in frame to the given one, also implements functionality for navigation buttons
    public void switchPanelTo(JPanel panel) {
        changePanel(panel);

        if (!(visitedPanels.contains(panel) | forwardPanels.contains(panel))) {
            forwardPanels.clear();
        }
        if (visitedPanels.isEmpty()) {
            visitedPanels.add(panel);
        } else if (!visitedPanels.getLast().equals(panel)) {
            visitedPanels.add(panel);
        }
    }

    //MODIFIES: this
    //EFFECTS: switches panel to previous panel
    public void previousPanel() {
        if (visitedPanels.size() > 1) {
            JPanel panel = visitedPanels.removeLast();
            forwardPanels.add(panel);
            switchPanelTo(visitedPanels.getLast());
        }
    }

    //MODIFIES: this
    //EFFECTS: switches panel to next panel if the user has previously gone back to another panel
    public void nextPanel() {
        if (!forwardPanels.isEmpty()) {
            JPanel panel = forwardPanels.getLast();
            switchPanelTo(panel);
            forwardPanels.removeLast();
        }
    }

    //MODIFIES: this
    //EFFECTS: creates ingredient and adds to generator
    public void addIngredient(String name, double quantity) {
        generator.createIngredient(name);
        generator.addQuantityToIngredient(name,quantity);
    }

    //MODIFIES: this
    //EFFECTS: cleans frame, switches panel to the given panel and repaints frame with title bar
    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        addTitle();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
        pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates title bar with navigation buttons and adds it to the frame
    private void addTitle() {
        JToolBar toolBar = new JToolBar("Main");
        addNavigationButtons(toolBar);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        add(toolBar,BorderLayout.PAGE_START);
    }

    //MODIFIES: toolBar
    //EFFECTS: adds navigation buttons to toolBar
    private void addNavigationButtons(JToolBar toolBar) {
        JButton button = null;
        button = makeNavigationButton("back24", "previous",
                "Previous");
        toolBar.add(button);
        button = makeNavigationButton("forward24", "forward",
                "Forward");
        toolBar.add(button);
    }

    //MODIFIES: this
    //EFFECTS: shows a dialog box that asks user to load generator from memory
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

    //MODIFIES: this
    //EFFECTS: loads json file representing generator from FILE_LOCATION
    private void loadGenerator() {
        try {
            JsonReader reader = new JsonReader(FILE_LOCATION);
            generator = reader.read();
        } catch (IOException e) {
            System.err.println("Please save to file first and try again.");
        }
    }

    //EFFECTS: shows a dialog box that asks user to save generator to memory
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

    //EFFECTS: saves json file representing generator to FILE_LOCATION
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


    //MODIFIES: this
    //EFFECTS: sets up frame
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

    //from java swing documentation
    //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ToolBarDemo2Project/src/components/ToolBarDemo2.java
    //EFFECTS: creates a JButton with given parameters
    private JButton makeNavigationButton(String imageName, String actionCommand, String altText) {

        String imgLocation = ICON_DIR + imageName + ".png";

        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);

        ImageIcon imageIcon = new ImageIcon(imgLocation); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImg);  // transform it back

        button.setIcon(imageIcon);
        return button;
    }
}

