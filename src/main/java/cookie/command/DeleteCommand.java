package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to delete a task from the task list by its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a new {@code DeleteCommand} to delete a task at the specified index.
     *
     * @param index the index of the task to be deleted, where the index is 1-based
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the {@code DeleteCommand}, deleting the task at the specified index from the {@code TaskList}.
     * The method checks if the index is valid, removes the task, and updates the user interface
     * with confirmation of the deleted task and the current number of tasks.
     *
     * @param taskList the list of tasks from which a task will be deleted
     * @param ui the user interface object used to print the confirmation of deletion
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the confirmation of the deleted task and the updated task count
     * @throws CookieException if the index is out of bounds (i.e., less than or equal to 0, or greater than the list size)
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new CookieException("The task you want to delete does not exist");
        }

        String response = ui.printDeleteTask(taskList.getTask(index));
        taskList.delete(index);
        response = response + ui.printNoTasksInList(taskList.getTaskArrayList());
        return response;
    }
}
