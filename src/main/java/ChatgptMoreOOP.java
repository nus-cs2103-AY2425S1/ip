package application;

import Exception.EmptyDescriptionException;
import Tools.Parser;
import Tools.Storage;
import Tools.TaskList;
import Tools.Ui;

import java.io.FileNotFoundException;

/**
 * Main class for a more Object-Oriented version of the Chatgpt task management application.
 * This class initializes the necessary components and handles the main execution loop,
 * delegating command parsing and execution to specialized classes.
 */
public class ChatgptMoreOOP {

    /**
     * The main method to run the application.
     * It sets up the application by initializing UI, storage, and parsing components,
     * and processes user commands continuously until termination.
     *
     * @param args Command line arguments passed to the application.
     * @throws EmptyDescriptionException If an operation encounters an empty description where one is required.
     * @throws FileNotFoundException If the file necessary for storage operations cannot be found.
     */
    public static void main(String[] args) throws EmptyDescriptionException, FileNotFoundException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList tasks = storage.load();  // Load the existing tasks from storage
        Parser parser = new Parser(tasks, storage, ui);  // Initialize the parser with the loaded tasks and UI

        ui.showWelcome();  // Display welcome message to the user
        while (ui.hasNextLine()) {
            String line = ui.readCommand();  // Read user command
            parser.parse(line);  // Parse and execute the command
        }
    }
}
