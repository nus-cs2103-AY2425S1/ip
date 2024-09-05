package fishman;

import fishman.command.Command;
import fishman.exception.FishmanException;
import fishman.task.TaskList;
import fishman.utils.Parser;
import fishman.utils.Storage;
import fishman.utils.Ui;
import javafx.application.Platform;

import java.util.ArrayList;


/**
 * The main class for the Fishman bot.
 * This class initializes the user interface, task list and parser,
 * and manages the main program.
 */
public class Fishman {

    /** The task list object to store and manage tasks. */
    private TaskList tasks = new TaskList();
    /** The storage object used to handle file operations. */
    private final Storage storage = new Storage("./data/fishman.csv");
    /** The Ui object used to construct messages. */
    private final Ui ui = new Ui();

    /**
     * Processes user inputs and returns the appropriate response.
     *
     * @param input The user input to be processed.
     * @return A string containing the response from command execution or error message
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, tasks);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(tasks, ui);
        } catch (FishmanException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads task from the data file and saves them if specified. This method performs its actions based on the
     * specified action parameter. "load" to load tasks from the data file and "save" to save the current tasks to
     * the data file.
     *
     * @param action A string indicating the action to be taken.
     * @return A string containing the result of the operation or any error messages.
     */
    public String loadAndSaveTasks(String action) {
        try {
            Storage.LoadResults output = storage.load();
            String errorMessage = output.getErrorMessage();
            switch (action) {
            case "load":
                tasks = output.getValidTasks();
                return errorMessage;

            case "save":
                if (errorMessage == null || errorMessage.isEmpty()) {
                    storage.save(tasks, new ArrayList<>());
                    return "successfully saved file.";
                } else {
                    storage.save(tasks, output.getAllTasksLines());
                    return "successfully saved file with corrupt lines";
                }
            default:
                return "Invalid action specified.";
            }

        } catch (Exception e) {
            return "An unexpected error has occurred: " + e.getMessage();
        }
    }

}

