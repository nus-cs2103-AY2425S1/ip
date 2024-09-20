package casper;

import java.io.File;

import casper.components.Processor;
import casper.components.Storage;
import casper.components.Stringifier;
import casper.components.TaskList;
import casper.exceptions.CorruptedSavedTasksException;
import casper.exceptions.CustomIOException;
import casper.exceptions.ExceptionWithSolution;

/**
 * Represents the main class for the Casper application.
 * This class handles the loading of saved tasks,
 * generating welcome messages, and processing user input.
 */
public class Casper {

    /**
     * The file path to the saved tasks file.
     */
    public static final String FILE_PATH = "casper.txt";

    private TaskList taskList;

    public Casper() {
        this.taskList = new TaskList();
    }

    /**
     * Loads the saved tasks from the file specified by {@code FILE_PATH}
     * and adds them to the {@code TaskList}.
     * Returns a success message if the tasks are loaded successfully,
     * or an error message if an exception occurs.
     *
     * @return A message indicating the result of the operation.
     */
    public String loadSavedTasks() {
        try {
            Storage.addSavedTasksToTaskList(FILE_PATH, taskList);
            assert new File(FILE_PATH).exists();
            return "Loaded saved tasks successfully!";
        } catch (CustomIOException | CorruptedSavedTasksException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a welcome message for the user.
     *
     * @return A welcome message as a {@code String}.
     */
    public String welcomeUser() {
        return Stringifier.getWelcomeMessage();
    }

    /**
     * Processes the user input and returns the corresponding response.
     * Handles various exceptions and returns their messages if any occur.
     *
     * @param input The user input to be processed.
     * @return The response generated from processing the input as a {@code String}.
     */
    public String getResponse(String input) {
        try {
            return Processor.processInput(input, taskList);
        } catch (ExceptionWithSolution | CustomIOException e) {
            return e.getMessage();
        }
    }
}
