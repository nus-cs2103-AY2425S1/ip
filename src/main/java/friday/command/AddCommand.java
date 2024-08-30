package friday.command;

import java.io.IOException;

import friday.task.Task;
import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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

    @Override
    public boolean isExit() {
        return false;
    }
}
