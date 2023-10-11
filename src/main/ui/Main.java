package ui;

//main runnable file of the program
public class Main {
    public static void main(String[] args) {
        RecipeGeneratorConsole console = new RecipeGeneratorConsole();
        while (console.getShouldProgramContinueRunning()) {
            console.mainInterface();
        }
    }
}
