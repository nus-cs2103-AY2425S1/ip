package morgana.commands;

import static morgana.util.DateTimeUtil.COMPACT_PATTERN;
import static morgana.util.DateTimeUtil.parseDateTime;

import morgana.exceptions.MorganaException;
import morgana.task.Event;
import morgana.task.Task;

/**
 * Represents a command to add an {@link Event} to the task list.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = """
            Invalid %1$s format.
            Usage: %1$s <description> /from <%2$s> /to <%2$s>
            Example: %1$s project meeting /from 2019-10-15 1400 /to 2019-10-15 1600
            """.formatted(COMMAND_WORD, COMPACT_PATTERN);

    /**
     * Constructs an {@code EventCommand} with the specified arguments.
     *
     * @param args The string containing the task description, start date/time, and end date/time.
     */
    public EventCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String args) throws MorganaException {
        String[] fields = args.split(" /from | /to ");
        if (fields.length != 3) {
            throw new MorganaException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new Event(fields[0], parseDateTime(fields[1]), parseDateTime(fields[2]));
    }
}
