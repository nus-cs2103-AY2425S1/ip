package taskon.commands;

import taskon.exception.TaskonException;
import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 * <p>
 * This class handles unmarking a specified task as not done, updating the user
 * interface to reflect the change, and saving the updated task list to storage.
 * </p>
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param index The index of the task to unmark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark a task as not done.
     * <p>
     * This method unmarks the task at the specified index as not done, updates the user
     * interface to show the unmarked task, and saves the updated task list to storage.
     * </p>
     *
     * @param taskList The list of tasks where the task will be unmarked as not done.
     * @param ui       The user interface that handles the display of messages to the user.
     * @param storage  The storage that handles saving the updated task list.
     * @return A string message that confirms the unmarking of a task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(this.index).markAsUndone();
            storage.saveTasks(taskList);
            return ui.unmark(taskList.getTask(this.index));
        } catch (IndexOutOfBoundsException e) {
            return "You only have " + taskList.size() + " tasks!\n";
        } catch (TaskonException e) {
            return e.getMessage();
        }
    }
}
