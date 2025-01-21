package morgana.commands;

import static morgana.util.DateTimeUtil.COMPACT_PATTERN;
import static morgana.util.DateTimeUtil.parseDateTime;

import morgana.exceptions.MorganaException;
import morgana.task.Deadline;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to add a {@link Deadline} to the task list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = """
            Invalid %1$s format.
            Usage: %1$s <description> /by <%2$s>
            Example: %1$s return book /by 2019-10-15 1800
            """.formatted(COMMAND_WORD, COMPACT_PATTERN);

    /**
     * Constructs a {@code DeadlineCommand} with the specified arguments.
     *
     * @param args The string containing the task description and due date.
     */
    public DeadlineCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String args, TaskList tasks) throws MorganaException {
        String[] fields = args.split(" /by ");
        if (fields.length != 2) {
            throw new MorganaException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new Deadline(fields[0], parseDateTime(fields[1]));
    }
}
