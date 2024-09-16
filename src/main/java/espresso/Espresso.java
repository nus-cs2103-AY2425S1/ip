package espresso;

import espresso.task.TaskList;
import espresso.storage.Storage;
import espresso.parser.Parser;
import espresso.command.InvalidCommandException;
import espresso.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents the main class for the Espresso chatbot.
 * This class initializes the user interface, storage, and task list, and manages
 * the application's command input and output.
 */
public class Espresso {

    /**
     * The main method for the Espresso application. It initializes necessary components,
     * loads tasks from storage, processes user input, and saves tasks to storage before exiting.
     *
     * @param args Command-line arguments
     * @throws InvalidCommandException If there is an invalid command during parsing.
     * @throws ParseException If there is an error in parsing dates for tasks.
     */
    public static void main(String[] args) throws InvalidCommandException, ParseException {
        Ui ui = new Ui();
        ui.printWelcome(); // Print a welcome greeting

        // Initialize storage and task list
        Storage storage = new Storage("./data/Espresso.txt");
        TaskList taskList = new TaskList();

        // Try to load tasks
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("Error loading tasks from file: " + e.getMessage());
        } catch (ParseException e) {
            ui.printError("Error in parsing tasks: " + e.getMessage());
        } catch (InvalidCommandException e) {
            ui.printError("Erratic Command: " + e.getMessage());
        }

        // Continuously read and process user input until 'bye' is entered
        while (true) {
            String input = ui.readCommand();
            if (input.equals("bye")) {
                break;
            }
            try {
                Parser.parse(input, taskList, ui); // Parse the user's input
            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            }
        }

        // Save tasks to storage before exiting
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.printError("Cannot save file due to error: " + e.getMessage());
        }

        // Print a goodbye greeting
        ui.printGoodbye();
    }
}
