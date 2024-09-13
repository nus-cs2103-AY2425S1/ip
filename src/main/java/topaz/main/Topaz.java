package topaz.main;

import java.io.IOException;

import javafx.application.Application;
import topaz.command.Command;
import topaz.exception.InvalidCommandException;
import topaz.ui.MainUi;
import topaz.ui.Ui;

/**
 * Represents the main application class for managing tasks.
 * The {@link Topaz} class is responsible for initializing and running the application,
 * interacting with the user, managing tasks, and handling storage operations.
 */
public class Topaz {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    private String commandType = "";

    /**
     * Enum representing the different types of tasks.
     * E represents Event, D represents Deadline, T represents Todo.
     */
    public enum TaskType {
        E, // Event
        D, // Deadline
        T, // Todo
    }

    /**
     * Constructs a {@link Topaz} instance with the specified file path for storage.
     * Initializes the user interface, storage, and loads existing tasks from the storage file.
     * If loading tasks fails, initializes an empty {@link TaskList}.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Topaz(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showInitializeIoeException(e);
            taskList = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            commandType = c.getClass().getSimpleName();
            return c.execute(taskList, ui, storage);
        } catch (InvalidCommandException e) {
            return ui.showException(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(MainUi.class, args);
    }

    public String getCommandType() {
        return commandType;
    }
}
