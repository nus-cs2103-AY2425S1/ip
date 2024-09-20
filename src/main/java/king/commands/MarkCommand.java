package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command that marks a specific task as done.
 * This command updates the task status to completed in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done, provided as a string.
     */
    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param tasks The task list where the task is to be marked as done.
     * @param ui The user interface used to show the status of the task.
     * @param storage The storage used to save the updated task list.
     * @throws KingException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        try {
            tasks.markTaskAsDone(taskIndex);
            storage.save(tasks.getTaskList());
            return ui.showTaskMarked(tasks.getTask(taskIndex));
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
