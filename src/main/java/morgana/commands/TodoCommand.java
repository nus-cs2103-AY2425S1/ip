package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.task.Task;
import morgana.task.Todo;

/**
 * Represents a command to add a {@link Todo} to the task list.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a {@code TodoCommand} with the specified arguments.
     *
     * @param args The string containing the task description.
     */
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String description) throws MorganaException {
        if (description.isEmpty()) {
            throw new MorganaException("Please enter a description for your todo.");
        }
        return new Todo(description);
    }
}
