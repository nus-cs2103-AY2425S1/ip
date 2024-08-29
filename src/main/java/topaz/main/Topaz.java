package topaz.main;

import java.io.IOException;

import topaz.command.Command;
import topaz.exception.InvalidCommandException;
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

    /**
     * Runs the main loop of the application.
     * Continuously reads commands from the user, parses them into {@link Command} objects,
     * executes the commands, and handles any exceptions that occur.
     * The loop continues until an exit command is received.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showException(e);
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Topaz("data/Topaz.txt").run();
    }


}
