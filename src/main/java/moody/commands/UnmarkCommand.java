package moody.commands;

import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a specific task as not done.
 * This command updates the status of a task in the task list to not completed.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param tasks The task list where the task to be unmarked is located.
     * @param ui The user interface for displaying the status of the unmarked task.
     * @param storage The storage to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks.toArrayList());
        ui.showUnmarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
