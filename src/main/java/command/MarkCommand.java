package command;

import java.io.IOException;

import exception.ScheduloException;
import task.TaskList;
import util.Storage;


/**
 * The MarkCommand class represents a command to mark a task as done in the task list.
 * It extends the Command class and provides the implementation for marking tasks.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done in the task list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the task at the specified index as done
     * and saving the updated list to storage.
     *
     * @param tasks   The TaskList containing the task to be marked as done.
     * @param storage The Storage instance used to save the updated task list.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        String message;
        try {
            message = tasks.mark(this.index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ScheduloException("The task index provided is invalid.");
        }
        storage.save(tasks);
        return message;
    }
}
