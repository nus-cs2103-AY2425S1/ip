package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command that marks a task as undone in the task list.
 * This command updates the specified task to be not done and saves the updated task list.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as undone (1-based index).
     */
    public UnmarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * Executes the unmark command by marking the specified task as undone.
     *
     * @param tasks The task list where the task will be marked as undone.
     * @param ui The user interface used to show the status of the updated task.
     * @param storage The storage used to save the updated task list.
     * @throws KingException If the task index is invalid or there is an issue saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        try {
            tasks.markTaskAsUndone(taskIndex);
            storage.save(tasks.getTaskList());
            return ui.showTaskUnmarked(tasks.getTask(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("Invalid list number entered!");
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
