package hoshi.command;

import java.io.IOException;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * Utility class for command classes.
 */
public class CommandUtils {

    /**
     * Handles saving when required in command classes
     *
     * @param tasks    TaskList of 3 types of tasks that will be added to in this method.
     * @param storage  Storage that handles all input output of Hoshi
     * @param ui       Ui responsible for displaying text to the user
     */
    public static void handleSave(TaskList tasks, Storage storage, Ui ui) {
        try {
            // save tasks to storage
            storage.save(tasks);
        } catch (IOException e) {
            ui.displayError("Hoshi encountered a problem while saving the task list.");
        }
    }
}
