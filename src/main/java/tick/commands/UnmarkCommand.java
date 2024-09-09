package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.Task;
import tick.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int toUnmark;

    /**
     * Construct a UnmarkCommand with index of task to unmark.
     *
     * @param taskNumber The task number to unmark.
     */
    public UnmarkCommand(int taskNumber) {
        this.toUnmark = taskNumber;
    }

    /**
     * Execute the command to unmark a task as done.
     *
     * @param tasks The task list to unmark the task as done.
     * @param ui The user interface to show the result of the command.
     * @param storage The storage to save the updated task list.
     * @throws TickException If an error occurs while unmarking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        Task undoneTask = tasks.markTaskAsUndone(this.toUnmark);
        ui.showTaskUnmarked(undoneTask);
        storage.saveData(tasks.getAllTasks());
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
