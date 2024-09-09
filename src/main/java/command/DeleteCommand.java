package command;

import java.io.IOException;

import exception.ScheduloException;
import task.TaskList;
import util.Storage;


/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and provides the implementation for deleting tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the task at the specified index from the task list and
     * saving the updated list to storage.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param storage The Storage instance used to save the updated task list.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        String message;
        try {
            message = tasks.delete(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ScheduloException("Invalid item number given");
        }
        storage.save(tasks);
        return message;
    }
}
