package myapp.core;

import myapp.command.Parser;
import myapp.exception.RubyException;
import myapp.storage.Storage;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.ui.Ui;
import java.io.IOException;
import java.util.List;

/**
 * The {@code Ruby} class represents the core of the Ruby chatbot application.
 * It initializes the user interface, storage, parser, and task list.
 * The class provides methods to run the application, process user commands,
 * and interact with the task list.
 */
public class Ruby {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a new {@code Ruby} instance with the specified file path for storage.
     * Initializes the UI, storage, and parser components. Loads tasks from the storage
     * into the task list. If an error occurs during loading, initializes an empty task list.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Ruby(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            List<Task> tasks = storage.load();
            taskList = new TaskList(tasks);
        } catch (IOException | RubyException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Ruby chatbot application. Displays the welcome message,
     * then enters a loop to read and execute user commands until an exit command is received.
     * Catches and displays errors encountered during command processing.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                String response = parser.parse(command, taskList, ui, storage);
                ui.showResponse(response);
                isExit = parser.isExit(command);
            } catch (RubyException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * The main method to launch the Ruby chatbot application.
     * Creates a new instance of {@code Ruby} with the specified file path for tasks
     * and calls the {@code run} method to start the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Ruby("data/tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input, taskList, ui, storage);
        } catch (RubyException | IOException e) {
            return e.getMessage();
        }
    }
}
