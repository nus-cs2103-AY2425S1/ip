package beechat;

import beechat.command.Command;
import beechat.task.TaskList;
import beechat.util.Parser;
import beechat.util.Storage;
import beechat.util.Ui;

import java.lang.String;
import java.io.IOException;

/**
 * Contains the main class of the Beechat chatbot application.
 * Handles the initialization and running of the application.
 * Interact with Beechat using various Commands.
 */
public class Beechat {

    /** Handles storage-related operations such as saving and loading tasks. */
    private Storage storage;

    /** Manages the list of tasks in the application. */
    private TaskList tasks;

    /** Handles user interactions, and displays messages. */
    private Ui ui;

    /**
     * Initializes the Beechat chatbot application with the specified file path.
     * Loads the existing tasks from storage, if any.
     *
     * @param filePath The path to the file where existing tasks are stored.
     */
    public Beechat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input The user's input command.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        if (input.equals("list")) {
            return tasks.getAllTasksAsString();
        }
        if (input.equals("welcome")) {
            return ui.getWelcomeMessage();
        }
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.getLatestResponse();  // Get the latest response for the GUI
        } catch (Exception e) {
            return "Error: " + e.getMessage();  // Return error message if something goes wrong
        }
    }
}