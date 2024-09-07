package myapp.command;

import myapp.core.Storage;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents a command that deletes a task from the task list.
 * This command identifies the task to be deleted by its index in the list.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted from the task list. */
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param i The index of the task to be deleted.
     */
    public DeleteCommand(int i) {
        super();
        this.index = i;
    }

    /**
     * Executes the command by deleting the specified task from the task list.
     * The task list is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param storage The storage system used to save the updated task list.
     * @return A string message confirming the deletion of the task.
     * @throws IndexOutOfBoundsException If the specified index is out of bounds for the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.delete(index);
        saveTasks(tasks, storage);
        return printRemoveMessage(task, tasks.size());
    }

    /**
     * Returns false since a DeleteCommand does not terminate the application.
     *
     * @return false, indicating that this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints a message confirming the removal of the task and the updated size of the task list.
     *
     * @param task The task that was removed from the task list.
     * @param size The new size of the task list after the task was removed.
     * @return A string message confirming the task removal.
     */
    private String printRemoveMessage(Task task, int size) {
        return "Noted. I've removed this task:\n" + task
                + "\n" + "Now you have " + size
                + " tasks in the list";
    }
}
