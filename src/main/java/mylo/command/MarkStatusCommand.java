package mylo.command;

import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Tui;

/**
 * Represents a command to mark or unmark a task's completion status in the task list.
 * <p></p>
 * <p>The {@code MarkStatusCommand} can either mark a task as done or mark it as undone,
 * depending on the provided flag.</p>
 * <p></p>
 * <p>This command requires the task index to identify the task whose status will be updated.</p>
 *
 * @author cweijin
 */
public class MarkStatusCommand extends Command {
    private final boolean IS_MARK_COMMAND;
    private final int INDEX;

    /**
     * Constructs a {@code MarkStatusCommand} to mark or unmark a task's completion status.
     * <p></p>
     * <p>If {@code isMarkCommand} is {@code true}, the task will be marked as done.
     * If {@code false}, the task will be marked as undone.</p>
     *
     * @param isMarkCommand {@code true} to mark the task as done, {@code false} to mark it as undone.
     * @param index         The index of the task to be marked or unmarked.
     */
    public MarkStatusCommand(boolean isMarkCommand, int index) {
        this.IS_MARK_COMMAND = isMarkCommand;
        this.INDEX = index;
    }

    /**
     * Executes the command to update the task's completion status.
     * <p></p>
     * <p>If {@code isMarkCommand} is {@code true}, the task at the specified index will
     * be marked as done. Otherwise, it will be marked as undone.</p>
     *
     * @param list The task list containing the task to be updated.
     * @param tui   The user interface to display the result of the command execution.
     * @throws StorageOperationException If there is an issue with the storage during the operation.
     */
    @Override
    public String execute(TaskList list, Tui tui) throws StorageOperationException {
        return IS_MARK_COMMAND ? list.markTaskAsDone(INDEX) : list.markTaskAsUndone(INDEX);
    }
}
