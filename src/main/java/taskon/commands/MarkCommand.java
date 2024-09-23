package taskon.commands;

import taskon.exception.TaskonException;
import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * <p>
 * This class handles marking a specified task as done, updating the user
 * interface to reflect the change, and saving the updated task list to storage.
 * </p>
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int index;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done.
     * <p>
     * This method marks the task at the specified index as done, updates the user
     * interface to show the marked task, and saves the updated task list to storage.
     * </p>
     *
     * @param taskList The list of tasks where the task will be marked as done.
     * @param ui       The user interface that handles the display of messages to the user.
     * @param storage  The storage that handles saving the updated task list.
     * @return A string message that confirms the completion of a task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(this.index).markAsDone();
            storage.saveTasks(taskList);
            return ui.mark(taskList.getTask(this.index));
        } catch (IndexOutOfBoundsException e) {
            return "You only have " + taskList.size() + " tasks!\n";
        } catch (TaskonException e) {
            return e.getMessage();
        }
    }
}
