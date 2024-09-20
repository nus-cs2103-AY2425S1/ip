package nugget;

import nugget.command.*;
import nugget.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code Parser} class is responsible for parsing user input and converting it into {@code Command} objects.
 * It handles various types of commands such as adding tasks, marking tasks, deleting tasks, and more.
 */
public class Parser {

    private static final String DATE_FORMAT = "yyyy-MM-dd HHmm"; // Expected date format
    private TaskList tasks;

    /**
     * Constructs a {@code Parser} with the specified task list.
     *
     * @param tasks The list of tasks to be manipulated by the commands.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input and returns the corresponding {@code Command}.
     *
     * @param command The user input string.
     * @return The {@code Command} object corresponding to the user input.
     * @throws NuggetException If the user input is invalid or the command is unknown.
     */
    public Command parse(String command) throws NuggetException {
        String[] splitText = command.split(" ", 2);

        if (splitText.length < 2) {
            return handleSinglePartCommand(splitText[0]);
        }

        String action = splitText[0].trim();
        String details = splitText[1].trim();
        return createCommand(action, details);

    }

    private Command handleSinglePartCommand(String command) throws NuggetException {
        switch (command) {
        case "list":
            return new ListCommand();
        case "sort":
            return new SortListCommand();
        case "todo":
        case "deadline":
        case "event":
        case "find":
            throw new EmptyDescriptionException();
        default:
            throw new UnknownCommandException();
        }
    }

    private Command createCommand(String action, String details) throws NuggetException {
        switch (action) {
        case "mark":
            return new MarkTaskCommand(parseIndex(details));
        case "unmark":
            return new UnmarkTaskCommand(parseIndex(details));
        case "delete":
            return new DeleteTaskCommand(parseIndex(details));
        case "todo":
            return new AddTaskCommand(new Todo(details));
        case "deadline":
            return new AddTaskCommand(parseDeadline(details));
        case "event":
            return new AddTaskCommand(parseEvent(details));
        case "find":
            return new FindTaskCommand(details);
        default:
            throw new UnknownCommandException();
        }
    }
    /**
     * Parses the task index from the provided details string.
     *
     * @param details The details string containing the task index.
     * @return The zero-based task index.
     * @throws NuggetException If the index is invalid or missing.
     */
    private int parseIndex(String details) throws NuggetException {
        try {
            int index = Integer.parseInt(details.trim()) - 1;
            if ((index < 0) || (index >= tasks.size())) {
                throw new InvalidTaskNumberException();
            }
            assert index >= 0;
            assert index < tasks.size();
            return index;
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Parses the user input for a deadline task.
     *
     * @param details The user input string containing the task description and deadline.
     * @return A {@code Deadline} task.
     * @throws NuggetException If the input format is incorrect.
     */
    private Task parseDeadline(String details) throws NuggetException {
        if (!details.contains(" /by ")) {
            throw new InvalidDeadlineFormatException();
        }
        String[] parts = details.split(" /by ", 2);

        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        validateDate(parts[1].trim());
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the user input for an event task.
     *
     * @param details The user input string containing the task description, start time, and end time.
     * @return An {@code Event} task.
     * @throws NuggetException If the input format is incorrect.
     */
    private Task parseEvent(String details) throws NuggetException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new InvalidEventFormatException();
        }
        String[] parts = details.split(" /from ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new EmptyDescriptionException();
        }
        validateDate(timeParts[0]);
        validateDate(timeParts[1]);
        return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }

    private void validateDate(String date) throws InvalidDateFormatException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateFormatException(); // Custom exception for invalid date format
        }
    }
}
