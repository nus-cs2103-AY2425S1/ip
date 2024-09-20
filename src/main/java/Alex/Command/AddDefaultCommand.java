package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.DefaultTask;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to add a default task to the TaskList and save it to storage.
 */
public class AddDefaultCommand extends CommandBase {
    private final DefaultTask task;

    /**
     * Constructs an AddDefaultCommand with the specified default task.
     * @param task The default task to be added.
     */
    public AddDefaultCommand(DefaultTask task) {
        this.task = task;
    }

    /**
     * Executes the AddDefaultCommand by adding the specified task to the TaskList,
     * updating the Ui with a success message, and saving the updated task list to storage.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance responsible for displaying output to the user.
     * @param storage The Storage instance used to save the updated task list.
     * @throws AlexException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        String message = tasks.addTask(task); // Get the message from TaskList
        ui.appendMessage(message); // Append it to the Ui
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

