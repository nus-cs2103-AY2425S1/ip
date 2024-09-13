package hoshi.command;

import java.io.IOException;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * Utility class for command classes
 */
public class CommandUtils {

    /**
     * Utility method for handling save when required in command classes
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
