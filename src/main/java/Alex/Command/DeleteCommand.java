package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to delete a task from the TaskList by index and save the updated list to storage.
 */
public class DeleteCommand extends CommandBase {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the TaskList,
     * updating the Ui with a success message, and saving the updated task list to storage.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance responsible for displaying output to the user.
     * @param storage The Storage instance used to save the updated task list.
     * @throws AlexException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        try {
            // Get the delete confirmation message from TaskList
            String message = tasks.deleteTask(index);
            // Pass the message to Ui for display
            ui.appendMessage(message);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new AlexException("Task not found.");
        }
    }
}
