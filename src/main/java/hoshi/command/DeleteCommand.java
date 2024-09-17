package hoshi.command;

import hoshi.exception.HoshiException;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * DeleteCommand where the logic for deleting a task is handled.
 */
public class DeleteCommand implements Command {

    /**
     * Stores the task index of the task to be deleted.
     */
    private final int taskIndex;

    /**
     * Constructs a new instance of DeleteCommand.
     *
     * @param taskIndex the index of the task to be deleted.
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
            // invalid index
            if (taskIndex >= tasks.size() || taskIndex < 0) {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }
            // delete and display deleted task name
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            CommandUtils.handleSave(tasks, storage, ui);

            return ui.displayTaskDeleted(task);
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }
}
