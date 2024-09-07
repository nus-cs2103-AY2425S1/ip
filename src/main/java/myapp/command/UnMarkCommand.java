package myapp.command;

import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents a command that marks a task as not done in the task list.
 * This command identifies the task by its index in the list and marks it as not completed.
 */
public class UnMarkCommand extends Command {

    /** The index of the task to be marked as not done in the task list. */
    private int index;

    /**
     * Constructs an UnMarkCommand with the specified task index.
     *
     * @param i The index of the task to be marked as not done.
     */
    public UnMarkCommand(int i) {
        super();
        this.index = i;
    }

    /**
     * Executes the command by marking the specified task as not done in the task list.
     * The updated task list is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list containing the task to be marked as not done.
     * @param storage The storage system used to save the updated task list.
     * @return A string message confirming that the task has been marked as not done.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        saveTasks(tasks, storage);
        return printMarkMessage(task);
    }

    /**
     * Returns false since an UnMarkCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints a message confirming that the task has been unmarked.
     *
     * @param task The task that was unmarked.
     * @return A string message confirming the task has been unmarked as incomplete.
     */
    private String printMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }
}
