package friday.command;

import friday.Storage;
import friday.task.Task;
import friday.TaskList;
import friday.Ui;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 * Inherits from the Command class and implements the execute method to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * Updates the task list, shows the added task to the user, and saves the updated task list.
     *
     * @param tasks  The TaskList to which the task is to be added.
     * @param ui     The Ui object for displaying messages to the user.
     * @param storage The Storage object for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getSize());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("An error occurred while saving the task.");
        }
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}