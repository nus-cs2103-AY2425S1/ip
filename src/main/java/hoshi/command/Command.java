package hoshi.command;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * Command interface where respective children command classes will implement from to handle various logic.
 */
public interface Command {
    /**
     * Handles the general execute method to be implemented by classes that implement this interface
     *
     * @param tasks    TaskList of 3 types of tasks that will be added to in this method.
     * @param storage  Storage that handles all input output of Hoshi
     * @param ui       Ui responsible for displaying text to the user
     * @return response that corresponds to user command or input
     */
    String execute(TaskList tasks, Ui ui, Storage storage);
}
