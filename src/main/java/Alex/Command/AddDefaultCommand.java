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

