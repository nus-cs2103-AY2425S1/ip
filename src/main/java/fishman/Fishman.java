package fishman;

import java.util.ArrayList;
import java.util.List;

import fishman.command.Command;
import fishman.exception.FishmanException;
import fishman.task.TaskList;
import fishman.utils.Parser;
import fishman.utils.Storage;
import fishman.utils.Ui;
import javafx.application.Platform;

/**
 * The main class for the Fishman bot.
 * This class initializes the user interface, task list and parser,
 * and manages the main program.
 */
public class Fishman {
    private TaskList taskList = new TaskList();
    private final Storage storage = new Storage("./data/fishman.csv");
    private final Ui ui = new Ui();

    /**
     * Processes user inputs and returns the appropriate response.
     *
     * @param input The user input to be processed.
     * @return A string containing the response from command execution or error message
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        assert taskList != null : "Task list should not be null before processing";
        try {
            Command command = Parser.parse(input, taskList);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(taskList, ui);
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
        assert action != null : "Action should not be null";

        try {
            Storage.LoadResults output = storage.load();
            assert output != null : "LoadResults should not be null";
            String errorMessage = output.getErrorMessage();

            switch (action) {
            case "load":
                taskList = output.getValidTasks();
                assert taskList != null : "Valid tasks should not be null after loading";
                return errorMessage;
            case "save":
                return saveTasks(errorMessage, output);
            default:
                return "Invalid action specified.";
            }

        } catch (Exception e) {
            return "An unexpected error has occurred: " + e.getMessage();
        }
    }

    /**
     * Saves the current task list to data file, handling corrupt lines if any.
     * This method checks if there are any error messages, which indicate that there are corrupted lines.
     * If there are no corrupted lines, the task list is saved as is. However, if there are corrupted lines, they
     * are appended to the end of the file.
     *
     * @param errorMessage A string containing an error message related to corrupted lines.
     * @param output A storage object containing the results of loading tasks from data file.
     * @return A string containing the result of the operation.
     */
    private String saveTasks(String errorMessage, Storage.LoadResults output) {
        List<String> corruptedLines = output.getCorruptedLines();

        if (errorMessage == null || errorMessage.isEmpty()) {
            storage.save(taskList, new ArrayList<>());
            return "Successfully saved file.";
        } else {
            assert corruptedLines != null : "Corrupted lines should not be null";

            storage.save(taskList, corruptedLines);
            return "Successfully saved file with corrupt lines.";
        }
    }
}

