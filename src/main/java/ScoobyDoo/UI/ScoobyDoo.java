package ScoobyDoo.UI;
import ScoobyDoo.Command.Command;
import ScoobyDoo.Parser.Parser;
import ScoobyDoo.Undo.UndoHistory;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

import java.io.IOException;

/**
 * The ScoobyDoo class represents the main application for managing tasks.
 * It handles user input, task management, and interaction with storage and UI components.
 */
public class ScoobyDoo {
    /** The list of tasks managed by the application. */
    private TaskList taskList;
    /** The storage component for saving and loading tasks. */
    private final Storage storage;
    /** The UI component for handling user interface operations. */
    private final UI ui;

    /**
     * Constructs a new ScoobyDoo application with the specified file path for storage.
     *
     * @param FilePath The file path where tasks will be saved and loaded from.
     */
    public ScoobyDoo(String FilePath) {
        storage = new Storage(FilePath);
        ui = new UI();
        try {
            taskList = new TaskList(storage.load(), new UndoHistory(10));
        } catch (IOException e) {
            ui.printErrorMessage("cannot parse data from file");
            taskList = new TaskList();
        } finally {
            assert taskList != null : "taskList should not be null";
        }
    }

    /**
     * Runs the main application loop.
     * This method handles user input, processes commands, and manages tasks until the user exits.
     * It also loads existing tasks from storage at startup and saves tasks before exiting.
     */
    public String getResponse (String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (InputFormatException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}

