package hoshi.command;

import hoshi.exception.HoshiException;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * MarkCommand where the logic for marking a task is handled
 */
public class UnmarkCommand implements Command {

    /**
     * task index of the task to be unmarked
     */
    private final int taskIndex;

    /**
     * MarkCommand constructor
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the logic for unmarking a task
     *
     * @param tasks   The TaskList that stores 3 types of tasks
     * @param ui      Ui that handles all user interaction
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
            tasks.get(taskIndex).setIsDone(false);
            CommandUtils.handleSave(tasks, storage, ui);
            return ui.displayTaskUnmarked(tasks.get(taskIndex));
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }
}
