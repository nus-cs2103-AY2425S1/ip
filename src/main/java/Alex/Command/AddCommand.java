package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to add a task to the TaskList and save it to storage.
 */
public class AddCommand extends CommandBase {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        String message = tasks.addTask(task); // Get the message from TaskList
        ui.appendMessage(message); // Append it to the Ui
        storage.save(tasks.getAllTasks());
    }
}

