package garfield.commands;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a task from the task list
 * in the Garfield chatbot application.
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Constructs a new DeleteCommand with the specified task ID.
     *
     * @param taskId The ID of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the task list,
     * displaying a message to the user, and saving the updated task list to storage.
     *
     * @param taskList The TaskList from which the task will be deleted.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GarfieldException {
        ui.showMessage("Alright you've got 1 less garfield.task.\n\n\t"
                + taskList.delete(taskId) + "\n\nEnjoy the extra ‘fun’ —or whatever you call it.");
        storage.save(taskList);

    }
}
