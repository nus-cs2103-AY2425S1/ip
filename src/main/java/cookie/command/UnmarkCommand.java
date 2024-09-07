package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents a command to unmark a task as not completed in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a new {@code UnmarkCommand} to unmark the task at the specified index.
     *
     * @param index the index of the task to be unmarked, where the index is 1-based
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the {@code UnmarkCommand} by marking the task at the specified index as not completed
     * in the {@code TaskList}. The method updates the task status and returns a confirmation message
     * from the user interface.
     *
     * @param taskList the list of tasks where the task will be unmarked
     * @param ui the user interface object used to print the confirmation of the unmarked task
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the confirmation message that the task has been unmarked
     * @throws CookieException if the index is out of bounds (i.e., less than or equal to 0,
     *         or greater than the list size)
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        if (this.index <= 0 || this.index > taskList.getSize()) {
            throw new CookieException("The task you want to mark does not exist");
        }
        taskList.unmarkDone(index);
        return ui.printUnmarkTask(taskList.getTask(index));
    }
}
