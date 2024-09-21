package atreides.command;

import atreides.task.Task;
import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

/**
 * Represents a command to delete a task from the TaskList based on the specified index.
 */
public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command based on a specific index and returns a string response about the task removal.
     *
     * @param tasks The TaskList object managing the list of tasks.
     * @param ui The Ui object for user interaction.
     * @param storage The Storage object to manage data storage.
     * @return A response string that provides feedback about the deleted task and the current number of tasks.
     * @throws AtreidesException If the task index is invalid or any error occurs during task deletion.
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        Task task = tasks.delete(index);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "Task removed: \n"
                + task.toString().indent(2)
                + tasks.size() + plural + " in list\n";
        return response;
    }

    /**
     * Deletes the specific task based on its index
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtreidesException if index not found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        String response = executeString(tasks, ui, storage);
        ui.showMessage(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
