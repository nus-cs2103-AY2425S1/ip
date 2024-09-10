package Bot;

import java.util.Scanner;


/**
 * The Duke class serves as the main entry point for the bot application.
 * It handles user interaction, file management, and task management by using other classes.
 * The class initializes the necessary managers and the parser to process commands.
 */
public class Duke {
    private final String name = "Bot.Duke";
    private final ListManager dukeManager = new ListManager();
    private final FileManager dukeFileManager = new FileManager("src/main/java/data");
    private final Parser parser = new Parser(dukeManager, dukeFileManager);


    private void exit() {
        System.out.println("Bye! Hope to see you again.");
    }

    /**
     * Greets the user and starts processing commands in a loop.
     * Reads any existing data from the file first and continues to interact
     * with the user until an exit command is issued.
     */
    private void greet() {
        assert dukeFileManager != null : "FileManager is not initialized";
        assert parser != null : "Parser is not initialized";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + ". How can I assist you today?");
        dukeFileManager.readFile();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            parser.parseCommand(command);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
    }
}
