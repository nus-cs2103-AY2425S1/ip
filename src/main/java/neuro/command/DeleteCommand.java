package neuro.command;

import neuro.Ui;
import neuro.Storage;

import neuro.task.Task;
import neuro.task.TaskList;

/**
 * The {@code DeleteCommand} class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to delete the task at the index from the task list.
     *
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0) {
            ui.showError("Missing or invalid index for 'delete' command! Add a valid " +
                    "index for a task to delete, like 'delete 2'.");
            return;
        }

        try {
            Task task = tasks.removeTask(index - 1);
            storage.updateTaskFile(tasks);
            ui.showMessage("Ok, I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Index out of bounds! Try calling the command 'list' to " +
                    "verify the index of the desired task.");
        }
    }
}
