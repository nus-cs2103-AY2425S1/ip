package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.Task;
import neuro.task.TaskList;

/**
 * The {@code UnmarkCommand} class represents a command to unmark a task in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to unmark a task as not done at the index in the task list.
     * 
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0) {
            ui.showError("Missing or invalid index for 'unmark' command! Add a valid " +
                    "index for a task to unmark, like 'unmark 2'.");
            return;
        }

        try {
            Task task = tasks.getTask(index - 1);
            task.markUndone();
            storage.updateTaskFile(tasks);

            ui.showMessage("Ok! I've unmarked this task as done:");
            ui.showMessage(task.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Index out of bounds! Try calling the command 'list' to " +
                    "verify the index of the desired task.");
        }
    }
}
