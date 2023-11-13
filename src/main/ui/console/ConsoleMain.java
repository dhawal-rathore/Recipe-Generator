package ui.console;

//main runnable file of the program
public class ConsoleMain {
    public static void main(String[] args) {
        RecipeGeneratorConsole console = new RecipeGeneratorConsole();
        while (console.getShouldProgramContinueRunning()) {
            console.mainInterface();
        }
    }
}
