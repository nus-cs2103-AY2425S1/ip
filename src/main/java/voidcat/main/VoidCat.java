package voidcat.main;

import java.io.IOException;

import voidcat.exception.VoidCatException;
import voidcat.parser.Parser;
import voidcat.storage.Storage;
import voidcat.task.TaskList;
import voidcat.ui.Ui;

/**
 * Represents the main class of the Void Cat program.
 * It manages the overall flow of the program,
 * including initializing components, interacting with the user, and handling tasks.
 */
public class VoidCat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a Void object with a specified file path for storage of tasks.
     * If file exists, tasks are loaded by Storage.
     * If not, the file is created.
     *
     * @param filePath The path to the file where user's tasks are stored.
     */
    public VoidCat(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    /**
     * Handles user input and gets the response.
     * Asserts tasks are initialized before processing.
     *
     * @param input The user's input command.
     * @return Response to user input.
     */
    public String getResponse(String input) {
        try {
            this.tasks = new TaskList(storage.load());
            if (input.equalsIgnoreCase("bye")) {
                return Ui.goodbye();
            }
            assert tasks != null : "TaskList is not initialized!";
            return new Parser().parseAndExecute(input, tasks, ui, storage);

        } catch (VoidCatException e) {
            return e.getMessage();
        } catch (IOException i) {
            return "Error saving tasks to file: " + i.getMessage();
        } catch (SecurityException s) {
            return "Security error in creating file or directory: " + s.getMessage();
        }
    }
}
