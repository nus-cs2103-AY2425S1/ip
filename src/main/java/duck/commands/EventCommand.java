package duck.commands;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duck.common.Utils;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Event;
import duck.storage.Storage;
import duck.ui.Ui;


/**
 * Represents a command to add an event task to the task list.
 * The event task contains a description, a start time, and an end time.
 */
public class EventCommand extends Command {


    private static final String INVALID_EVENT_DURATION = "The end time of an event should be after the start time!\n";
    private static final String PATTERN_EVENT_COMMAND = "(?i)^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$";
    private static final String ERROR_MESSAGE_EVENT_COMMAND = """
            Give me a valid event format!
            event {description} /from {start} /to {end}
            {start}: yyyy-mm-dd HHmm OR yyyy/mm/dd HHmm
            {end}: yyyy-mm-dd HHmm OR yyyy/mm/dd HHmm
            """;

    /**
     * Constructs an EventCommand with the specified message.
     *
     * @param message The message containing the details of the event.
     */
    public EventCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by parsing the event details from the message and
     * adding the event task to the task list.
     *
     * @param tasks The list of tasks to which the event task is added.
     * @param storage The storage system for saving and loading tasks.
     * @param ui The user interface for displaying messages to the user.
     * @throws DuckException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        Event event = parseEvent(message);
        tasks.addTask(event, storage);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the EventCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the input string to create an Event object.
     * The input string must follow the pattern: "event {description} /from {start} /to {end}".
     *
     * @param input The input string containing the event details.
     * @return The Event object created from the input string.
     * @throws DuckException If the input string does not match the expected pattern or if
     *                       the end time is not after the start time.
     */
    private Event parseEvent(String input) throws DuckException {
        Pattern pattern = Pattern.compile(PATTERN_EVENT_COMMAND);
        Matcher matcher = pattern.matcher(input);

        return createEvent(matcher);
    }

    private Event createEvent(Matcher matcher) throws DuckException {
        if (matcher.matches()) {
            String description = matcher.group(1);
            String fromStr = matcher.group(2);
            String toStr = matcher.group(3);

            LocalDateTime from = Utils.convertToDateTime(fromStr);
            LocalDateTime to = Utils.convertToDateTime(toStr);

            if (!to.isAfter(from)) {
                throw new DuckException(INVALID_EVENT_DURATION);
            } else {
                return new Event(description, from, to);
            }
        } else {
            throw new DuckException(ERROR_MESSAGE_EVENT_COMMAND);
        }
    }
}
