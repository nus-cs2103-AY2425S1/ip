package Bot;

import java.util.Scanner;


/**
 * The Duke class serves as the main entry point for the bot application.
 * It handles user interaction, file management, and task management by using other classes.
 * The class initializes the necessary managers and the parser to process commands.
 */
public class Duke {
    private static String name = "Bot.Duke";
    private ListManager DukeManager = new ListManager();

    private FileManager DukeFileManager = new FileManager("src/main/java/data");
    private Parser parser = new Parser(DukeManager, DukeFileManager);

    private void exit() {
        System.out.println("Bye! Hope to see you again.");
    }

    /**
     * Greets the user and starts processing commands in a loop.
     * Reads any existing data from the file first and continues to interact
     * with the user until an exit command is issued.
     */
    private void greet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + ". How can I assist you today?");
        DukeFileManager.readFile();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            parser.parseCommand(command);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
    }
}
