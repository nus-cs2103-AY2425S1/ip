package myapp.command;

import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents a command that marks a task as done in the task list.
 * This command identifies the task by its index in the list and marks it as completed.
 */
public class MarkCommand extends Command {

    /** The index of the task to be marked as done in the task list. */
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param i The index of the task to be marked as done.
     */
    public MarkCommand(int i) {
        super();
        this.index = i;
    }

    /**
     * Executes the command by marking the specified task as done in the task list.
     * The updated task list is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list containing the task to be marked as done.
     * @param storage The storage system used to save the updated task list.
     * @return A string message confirming that the task has been marked as done.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.markAsDone();
        saveTasks(tasks, storage);
        return printMarkMessage(task);
    }

    /**
     * Returns false since a MarkCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints a message confirming that the task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A string message confirming the task has been marked as done.
     */
    private String printMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }
}
