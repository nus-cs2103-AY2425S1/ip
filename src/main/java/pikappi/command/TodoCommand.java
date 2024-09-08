package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;
import pikappi.task.TodoTask;

/**
 * Represents a command by user to add a todo task.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Creates a new TodoCommand object.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the todo task to the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     * @throws PikappiException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        assert tasks != null : "TaskList cannot be null";
        return tasks.addTask(new TodoTask(description));
    }
}
