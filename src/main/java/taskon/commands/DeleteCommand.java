package taskon.commands;

import static taskon.common.Messages.MESSAGE_INVALID_INTEGER;

import taskon.storage.Storage;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * This class handles the deletion of a task from the task list at a specified index.
 * It also updates the user interface to show that the task has been deleted and
 * saves the updated task list to storage.
 * </p>
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     * <p>
     * This constructor initializes the command with the index of the task
     * that is to be deleted from the task list.
     * </p>
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the command to delete a task from the task list.
     * <p>
     * This method retrieves the task at the specified index, deletes it from the task list,
     * updates the user interface to show the deleted task, and saves the updated task list
     * to storage. If the index is out of bounds or not a valid integer, appropriate error
     * messages are displayed.
     * </p>
     *
     * @param taskList The list of tasks from which the task will be deleted.
     * @param ui       The user interface that handles the display of messages to the user.
     * @param storage  The storage that handles saving the updated task list.
     * @return A string message that confirms the deletion of the task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            storage.saveTasks(taskList);
            return ui.showDeleted(task, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            return "You only have " + taskList.size() + " tasks!\n";
        } catch (NumberFormatException e) {
            return MESSAGE_INVALID_INTEGER;
        }
    }
}
