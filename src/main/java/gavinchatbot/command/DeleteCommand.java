package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing a task from the task list.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The UI that will display the task deletion to the user.
     * @param storage The storage where the updated task list will be saved.
     * @return A message indicating the task has been successfully deleted.
     * @throws GavinException If the task cannot be found or deleted.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return ui.showDeletedTask(task, tasks.size());
    }

    /**
     * Returns whether the command causes the application to exit.
     *
     * @return false as the delete command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
