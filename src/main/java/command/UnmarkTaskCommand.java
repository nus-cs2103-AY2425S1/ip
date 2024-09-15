package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that marks a specific task as not done.
 * The {@code UnmarkTaskCommand} updates the status of a task at a specified index
 * to indicate that it has not been completed.
 */
public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code UnmarkTaskCommand} with the specified task index.
     *
     * @param arguments The index of the task to be marked as not done, passed as a string.
     *                  This string is parsed into an integer to identify the task.
     */
    public UnmarkTaskCommand(String arguments) {
        this.taskIndex = Integer.parseInt((arguments));
    }

    /**
     * Executes the unmark task command.
     * This method marks the task at the specified index as not done in the task list.
     *
     * @param taskList The task list containing the tasks to be updated.
     * @param ui       The user interface object, which is not used in this command.
     * @param storage  The storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.unmarkAsDone(taskIndex);
    }
}
