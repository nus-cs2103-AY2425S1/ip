package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.Task;
import tick.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int toMark;

    /**
     * Constructs a MarkCommand with index of task to be marked as done.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.toMark = index;
    }

    /**
     * Marks the task as done and saves the updated task list to storage.
     *
     * @param tasks The task list to mark the task as done.
     * @param ui The user interface to show the result of the command.
     * @param storage The storage to save the updated task list.
     * @throws TickException If an error occurs while marking the task as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        assert tasks != null : "TaskList cannot be null.";
        Task doneTask = tasks.markTaskAsDone(this.toMark);
        ui.showTaskMarked(doneTask);
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
