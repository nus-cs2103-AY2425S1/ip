package main;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The main class for the ChattyBuddy application.
 * This class is responsible for initializing the application,
 * handling user input, and coordinating between the various components
 * such as UI, storage, and task lists management.
 */
public class ChattyBuddy {

    /**
     * The main entry point of the ChattyBuddy application.
     * This method initializes the UI, loads tasks from storage, and enters a loop
     * to continuously process user commands until the user exits by entering "bye".
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/chattybuddy.txt");
        TaskList taskList;

        // Shows welcome message to the user
        ui.showWelcomeMessage();

        // Attempts to load tasks from storage
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showErrorMessage("Data file not found. Starting with an empty task list.");
            taskList = new TaskList(new ArrayList<>());
        }
        String response = ui.readCommand();
        while (!response.equals("bye")) {
            try {
                Parser.parseCommand(response, taskList, ui, storage);
            } catch (InvalidInputException | EmptyTaskException | TaskIndexOutOfBound e) {
                ui.showErrorMessage(e.getMessage());
            }

            // Reads the next command from the user
            response = ui.readCommand();
        }

        // Displays goodbye message when user exits
        ui.showGoodbyeMessage();
    }
}

