package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.task.Todo;

/**
 * Represents a command to add a {@link Todo} to the task list.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_MISSING_DESCRIPTION =
            "Please enter a description for your %s.".formatted(COMMAND_WORD);

    /**
     * Constructs a {@code TodoCommand} with the specified arguments.
     *
     * @param args The string containing the task description.
     */
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String description, TaskList tasks) throws MorganaException {
        if (description.isEmpty()) {
            throw new MorganaException(MESSAGE_MISSING_DESCRIPTION);
        }
        return new Todo(description);
    }
}
