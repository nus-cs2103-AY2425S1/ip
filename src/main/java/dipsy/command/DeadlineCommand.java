package dipsy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.parser.DateParser;
import dipsy.task.Deadline;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * The command follows the format "deadline &lt;description&gt; /by &lt;date&gt;".
 * The date must be in the format "yyyy-MM-dd".
 */

public class DeadlineCommand extends Command {
    /** Regular exppression(regen) pattern to parse the deadline command input.**/
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");

    /**
     * Constructs a DeadlineCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public DeadlineCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the DeadlineCommand by parsing the user input and adding a new deadline task to the task list.
     * If the input is invalid or the date format is incorrect, exceptions are thrown.
     *
     * @throws InvalidCommandException If the command format is invalid.
     * @throws InvalidDateException If the provided date is invalid or incorrectly formatted.
     */
    @Override
    public String execute() throws InvalidCommandException, InvalidDateException {
        Matcher matcher = DEADLINE_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            try {
                LocalDate parsedBy = DateParser.parseDate(by);
                Deadline deadline = new Deadline(description, parsedBy);
                tasks.addTask(deadline);
                return ui.getTaskAddedMessage(deadline, tasks.getSize());
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DEADLINE);
        }
    }
}
