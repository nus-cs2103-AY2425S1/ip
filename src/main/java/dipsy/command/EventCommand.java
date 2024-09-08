package dipsy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.parser.DateParser;
import dipsy.task.Event;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * The command follows the format "deadline &lt;description&gt; /from &lt;date&gt; /by &lt;date&gt;".
 * The date must be in the format "yyyy-MM-dd".
 */
public class EventCommand extends Command {

    /** Regular expression pattern to parse the event command input. */
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    /**
     * Constructs a EventCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public EventCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the DeadlineCommand by parsing the user input and adding a new event task to the task list.
     * If the input is invalid or the date format is incorrect, exceptions are thrown.
     *
     * @throws InvalidCommandException If the command format is invalid.
     * @throws InvalidDateException If the provided date is invalid or incorrectly formatted.
     */
    @Override
    public String execute() throws InvalidDateException, InvalidCommandException {
        Matcher matcher = EVENT_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            try {
                LocalDate parsedStart = DateParser.parseDate(start);
                LocalDate parsedEnd = DateParser.parseDate(end);
                Event event = new Event(description, parsedStart, parsedEnd);
                tasks.addTask(event);
                return ui.getTaskAddedMessage(event, tasks.getSize());
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_EVENT);
        }
    }
}
