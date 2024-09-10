package hoshi.command;

import hoshi.exception.HoshiException;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * MarkCommand where the logic for marking a task is handled
 */
public class MarkCommand implements Command {

    /**
     * task index of the task to be marked
     */
    private final int taskIndex;

    /**
     * MarkCommand constructor
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the logic for marking a task
     *
     * @param tasks  the TaskList that stores 3 types of tasks
     * @param ui     Ui that handles all user interaction
     * @param storage Storage that handles all input output of Hoshi
     * @return response corresponding to the function that was called depending on user input
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // if specified is out of bounds
            if (taskIndex >= tasks.size()) {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }
            // else if not out of bounds
            tasks.get(taskIndex).setIsDone(true);
            CommandUtils.handleSave(tasks, storage, ui);
            return ui.displayTaskMarked(tasks.get(taskIndex));
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }
}
