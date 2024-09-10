package hoshi.command;

import hoshi.exception.HoshiException;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * DeleteCommand where the logic for deleting a task is handled
 */
public class DeleteCommand implements Command {

    /**
     * task index of the task to be deleted
     */
    private final int taskIndex;

    /**
     * MarkCommand constructor
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the logic for deleting a task
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
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            CommandUtils.handleSave(tasks, storage, ui);
            return ui.displayTaskDeleted(task);
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }
}
