package command;

import java.io.IOException;

import task.TaskList;
import task.Task;
import exception.ScheduloException;
import util.Storage;

/**
 * The AddCommand class represents a command to add a new task to the task list.
 * It extends the Command class and provides the implementation for adding tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list and saving the updated list to storage.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance used to interact with the user.
     * @param storage The Storage instance used to save the updated task list.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        String message = tasks.add(task);
        storage.save(tasks);
        return message;
    }
}
