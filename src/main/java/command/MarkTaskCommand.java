package command;

import storage.Storage;
import task.TaskList;

/**
 * Represents a command that marks a specific task as done.
 * The {@code MarkTaskCommand} updates the status of a task at a specified index
 * to indicate that it has been completed.
 */
public class MarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkTaskCommand} with the specified task index.
     *
     * @param arguments The index of the task to be marked as done, passed as a string.
     *                  This string is parsed into an integer to identify the task.
     */
    public MarkTaskCommand(String arguments) {
        this.taskIndex = Integer.parseInt((arguments));
    }

    /**
     * Executes the mark task command.
     * This method marks the task at the specified index as done in the task list.
     *
     * @param taskList The task list containing the tasks to be updated.
     * @param storage  The storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.markAsDone(taskIndex);
    }
}
