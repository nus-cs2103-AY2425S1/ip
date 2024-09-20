package bobby.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import bobby.command.AddTaskCommand;
import bobby.command.ByeCommand;
import bobby.command.Command;
import bobby.command.CommandEnum;
import bobby.command.DeleteTasksCommand;
import bobby.command.FindTasksCommand;
import bobby.command.ListCommand;
import bobby.command.MarkTasksCommand;
import bobby.command.SearchDateCommand;
import bobby.command.UnmarkTasksCommand;
import bobby.exceptions.BobbyException;
import bobby.exceptions.EmptyDeadlineException;
import bobby.exceptions.EmptyEventException;
import bobby.exceptions.EmptyTodoException;
import bobby.exceptions.InvalidDateException;
import bobby.exceptions.InvalidInputException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;


/**
 * The {@code Parser} class is responsible for interpreting and converting
 * user input into actionable commands and tasks. It contains methods to parse
 * user input strings to determine the appropriate {@code Command} or to create
 * specific types of {@code Task} objects, such as {@code Todo}, {@code Deadline},
 * and {@code Event}.
 * <p>
 * The {@code Parser} class also handles input validation and throws specific exceptions
 * when the input is invalid, incomplete, or improperly formatted. It ensures that the input
 * provided by the user meets the expected format for each task type and command.
 * </p>
 * <p>
 * This class is a key component of the application, facilitating user interaction by
 * interpreting input and guiding the flow of the program accordingly.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input to determine the command.
     * This method takes the input string provided by the user and attempts to
     * map it to a {@code Command} enum constant.
     *
     * @param userInput the input string from the user
     * @return the {@code Command} enum constant corresponding to the user's input
     * @see CommandEnum#fromString(String)
     */
    public CommandEnum parseCommand(String userInput) {
        return CommandEnum.fromString(userInput);
    }

    /**
     * Parses the user input and returns an array containing the task indices.
     * This method splits the input string by spaces and returns the arguments
     * starting from the second position onward, excluding the command keyword.
     * For example, if the input is "mark 1 2 3", this method will return
     * an array containing ["1", "2", "3"].
     *
     * @param userInput the raw input string from the user, where the first argument
     *                  is the command, and the remaining arguments are task indices.
     * @return an array of strings containing the task indices extracted from the input.
     */
    public String[] parseTaskIndices(String userInput) {
        String[] args = userInput.split(" ");
        return Arrays.copyOfRange(args, 1, args.length);
    }


    /**
     * Parses the provided date input and returns a {@code LocalDate} object.
     * This method handles special keywords such as "today" and "tomorrow",
     * as well as absolute dates in the ISO format (yyyy-MM-dd). If the input
     * cannot be parsed as a valid date, an {@code InvalidDateException} is thrown.
     * <p>
     * The method first checks if the input is a special keyword ("today" or
     * "tomorrow") and returns the corresponding date. If not, it attempts to
     * parse the input as an absolute date. If parsing fails, it throws an
     * {@code InvalidDateException}.
     * </p>
     *
     * @param input the date input as a {@code String}. This can be a special
     *              keyword ("today" or "tomorrow") or an absolute date in the
     *              format yyyy-MM-dd.
     * @return a {@code LocalDate} object representing the parsed date.
     * @throws InvalidDateException if the input is not a valid date or special
     *                               keyword, and cannot be parsed into a
     *                               {@code LocalDate}.
     */
    public LocalDate parseDate(String input) throws InvalidDateException {
        LocalDate today = LocalDate.now();

        if (input.equalsIgnoreCase("today")) {
            return today;
        } else if (input.equalsIgnoreCase("tomorrow")) {
            return today.plusDays(1);
        } else {
            try {
                // Try parsing as an absolute date
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        }
    }

    /**
     * Creates a {@code Todo} task based on the provided user input.
     *
     * @param userInput The user's input in the form of "todo {description}".
     * @return A {@code Todo} task.
     * @throws EmptyTodoException If the description of the todo task is empty.
     */
    public Task createTodo(String userInput) throws EmptyTodoException {
        if (userInput.length() <= 5 || userInput.substring(5).trim().isEmpty()) {
            throw new EmptyTodoException();
        }

        // Extract the description after "todo " and trim any leading or trailing spaces
        String description = userInput.substring(5).trim();
        return new Todo(description);
    }

    /**
     * Creates a {@code Deadline} task based on the provided user input.
     *
     * @param userInput The user's input in the form of "deadline {description} /by {yyyy-MM-dd}".
     * @return A {@code Deadline} task.
     * @throws EmptyDeadlineException If the description or deadline date is missing.
     * @throws InvalidDateException If the provided deadline date format is invalid.
     */
    public Task createDeadline(String userInput) throws EmptyDeadlineException, InvalidDateException {
        if (userInput.length() <= 9) {
            throw new EmptyDeadlineException(); // No description provided
        }
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new EmptyDeadlineException();
        }
        String description = parts[0];
        String byString = parts[1];
        try {
            LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Creates an {@code Event} task based on the provided user input.
     *
     * @param userInput The user's input in the form of "event {description} /from {yyyy-MM-dd} /to {yyyy-MM-dd}".
     * @return An {@code Event} task.
     * @throws EmptyEventException If the description, start date, or end date is missing.
     * @throws InvalidDateException If the provided date format is invalid.
     */
    public Task createEvent(String userInput) throws EmptyEventException, InvalidDateException {
        if (userInput.length() <= 6) {
            throw new EmptyEventException();
        }
        String[] parts = userInput.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            throw new EmptyEventException();
        }
        String description = parts[0];
        String fromString = parts[1];
        String toString = parts[2];
        try {
            LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Extracts the keyword for searching tasks from the user input.
     *
     * @param userInput The user's input in the form of "find {keyword}" or "searchdate {yyyy-MM-dd}".
     * @return The keyword or date string to be used in the search.
     * @throws InvalidInputException If the input is invalid or the keyword is missing.
     */
    public String findKeyword(String userInput) throws InvalidInputException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new InvalidInputException();
        }
        String command = parts[0];
        String argument = parts[1].trim(); // Extract the argument after "searchdate " or "find "
        assert command.equalsIgnoreCase("searchdate")
                || command.equalsIgnoreCase("find") : "Invalid command: " + command;
        return argument;
    }

    /**
     * Extracts and parses a date from the user input for the search date command.
     *
     * @param userInput The user's input in the form of "searchdate {yyyy-MM-dd}".
     * @return The {@code LocalDate} object representing the date.
     * @throws InvalidDateException If the provided date format is invalid.
     * @throws InvalidInputException If the date is missing in the input.
     */
    public LocalDate findDate(String userInput) throws InvalidDateException, InvalidInputException {
        String arg = findKeyword(userInput);
        return parseDate(arg);
    }

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param userInput The user's input string representing a command.
     * @return A {@code Command} object based on the user's input.
     * @throws BobbyException If the user input is invalid or does not match any command.
     */
    public Command parseUserCommand(String userInput) throws BobbyException {
        CommandEnum commandEnum = CommandEnum.fromString(userInput);
        Command command = switch (commandEnum) {
        case BYE -> new ByeCommand();
        case LIST -> new ListCommand();
        case MARK -> new MarkTasksCommand(parseTaskIndices(userInput));
        case UNMARK -> new UnmarkTasksCommand(parseTaskIndices(userInput));
        case DELETE -> new DeleteTasksCommand(parseTaskIndices(userInput));
        case TODO -> new AddTaskCommand(createTodo(userInput));
        case DEADLINE -> new AddTaskCommand(createDeadline(userInput));
        case EVENT -> new AddTaskCommand(createEvent(userInput));
        case FIND -> new FindTasksCommand(findKeyword(userInput));
        case SEARCHDATE -> new SearchDateCommand(findDate(userInput));
        default -> throw new InvalidInputException();
        };
        return command;
    }
}
