package Gary.command;

import java.io.IOException;

import Gary.GaryException;
import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;
import Gary.task.Task;

/**
 * Represents a command to edit the status of a task in the task list.
 * The task can either be marked as done or unmarked as not done.
 */
public class EditTaskCommand extends Command {

    // Flag indicating whether to mark or unmark the task as done
    private boolean isDone;

    // The index of the task to be edited
    private int index;

    /**
     * Constructs an {@code EditTaskCommand} object with the specified status and task index.
     *
     * @param isDone The flag indicating whether to mark or unmark the task as done.
     * @param index The index of the task to edit.
     */
    public EditTaskCommand(boolean isDone, int index) {
        assert index >= 0 : "Index should not be negative";
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Executes the edit task command, which marks or unmarks a task as done in the {@code TaskList},
     * updates the user through {@code Ui}, and saves the updated task list to storage.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks.
     * @return A message indicating the task has been marked or unmarked.
     * @throws GaryException If an error occurs while saving the task list or if the index is out of bounds.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GaryException {
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert index >= 0 && index < taskList.size() : "Index is out of bounds";

        try {
            Task task = taskList.getTask(index);
            assert task != null : "Task should not be null";

            if (isDone) {
                task.editStatus();
                storage.saveTask(taskList);
                return ui.markTask(task);
            } else {
                task.editStatus();
                storage.saveTask(taskList);
                return ui.unmarkTask(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GaryException("Task list index is out of bounds!");
        } catch (IOException e) {
            throw new GaryException("An error occurred while saving the task list. Try again.");
        }
    }

    /**
     * Indicates that the EditTaskCommand does not terminate the application.
     *
     * @return false as the edit command does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
