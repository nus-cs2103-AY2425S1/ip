package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, marking the task as not done in the task list.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param ui The UI that will display the unmarked task.
     * @param storage The storage that will save the updated task list.
     * @return A message indicating the task has been successfully unmarked.
     * @throws GavinException If the task index is invalid or an error occurs during unmarking.
     * @throws IOException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = tasks.getTask(index);
        tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
        return ui.showUnmarkedTask(tasks.getTask(index));
    }

    /**
     * Returns whether the command causes the application to exit.
     *
     * @return false as the unmark command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
