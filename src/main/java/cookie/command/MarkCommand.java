package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a new {@code MarkCommand} to mark the task at the specified index as completed.
     *
     * @param index the index of the task to be marked as completed, where the index is 1-based
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the {@code MarkCommand} by marking the task at the specified index as completed in the {@code TaskList}.
     * The method updates the task status and returns a confirmation message from the user interface.
     *
     * <p>The method checks if the index is valid; if it is out of bounds, a {@code CookieException} is thrown.</p>
     *
     * @param taskList the list of tasks where the task will be marked as completed
     * @param ui the user interface object used to print the confirmation of the marked task
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the confirmation message that the task has been marked as completed
     * @throws CookieException if the index is out of bounds (i.e., less than or equal to 0,
     *          or greater than the list size)
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new CookieException("The task you want to mark does not exist");
        }

        taskList.markDone(index);
        return ui.printMarkTask(taskList.getTask(index));
    }
}
