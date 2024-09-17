package trackbot.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import trackbot.TrackBotException;
import trackbot.commands.AddCommand;
import trackbot.commands.Command;
import trackbot.commands.DeleteCommand;
import trackbot.commands.ExitCommand;
import trackbot.commands.FindCommand;
import trackbot.commands.InvalidCommand;
import trackbot.commands.ListCommand;
import trackbot.commands.MarkCommand;
import trackbot.commands.UnknownCommand;
import trackbot.commands.UnmarkCommand;
import trackbot.task.Deadline;
import trackbot.task.Event;
import trackbot.task.Task;
import trackbot.task.ToDo;

/**
 * Parses user inputs and translate them into commands to be executed by trackbot.
 * The Parser class provides utility methods for parsing tasks from storage for display
 * and validating date formats.
 */
public class Parser {

    static final String DEFAULT_DATE_FORMAT = "MMM d yyyy";
    static final String DEFAULT_DATE_TIME_FORMAT = "MMM d yyyy HH:mm";
    static final String TODO = "todo";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String MARK = "mark";
    static final String UNMARK = "unmark";
    static final String DELETE = "delete";
    static final String LIST = "list";
    static final String FIND = "find";
    static final String BYE = "bye";

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param userInput The raw input string from the user.
     * @return The Command object representing the user input.
     * @throws TrackBotException If the input does not match any known commands or has an invalid format.
     */
    public Command parse(String userInput) throws TrackBotException {
        String[] splitInput = userInput.split(" ", 2);
        String commandWord = splitInput[0].toLowerCase();
        String otherWord = splitInput.length > 1 ? splitInput[1].trim() : "";

        return switch (commandWord) {
        case LIST -> attemptListCommand(otherWord);
        case TODO -> attemptToDoCommand(otherWord);
        case DEADLINE -> attemptDeadlineCommand(otherWord);
        case EVENT -> attemptEventCommand(otherWord);
        case DELETE -> attemptDeleteCommand(otherWord);
        case MARK -> attemptMarkCommand(otherWord);
        case UNMARK -> attemptUnmarkCommand(otherWord);
        case FIND -> attemptFindCommand(otherWord);
        case BYE -> new ExitCommand();
        default -> new UnknownCommand();
        };
    }

    /**
     * Attempts to parse the user input and returns a ListCommand if valid.
     *
     * @param otherWord The additional user input after the command word.
     * @return A ListCommand if the input is valid, or an InvalidCommand if additional input is present.
     */
    private static Command attemptListCommand(String otherWord) {
        if (!otherWord.isEmpty()) {
            return new InvalidCommand(LIST, LIST);
        }
        return new ListCommand();
    }

    /**
     * Attempts to parse the user input for a ToDo task and returns an AddCommand if valid.
     *
     * @param otherWord The task description provided by the user.
     * @return An AddCommand with a ToDo task if the input is valid, or an InvalidCommand if no description is provided.
     */
    private static Command attemptToDoCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand(TODO, "todo <task description>");
        }
        return new AddCommand(new ToDo(otherWord));
    }

    /**
     * Attempts to parse the user input for a Deadline task and returns an AddCommand if valid.
     *
     * @param otherWord The task description and due date provided by the user.
     * @return An AddCommand with a Deadline task if the input is valid,
     *     or an InvalidCommand if the format is incorrect.
     */
    private static Command attemptDeadlineCommand(String otherWord) {
        String[] deadlineParts = otherWord.split(" /by ");
        if (deadlineParts.length < 2) {
            return new InvalidCommand(DEADLINE, "deadline <task description> /by <date/time>");
        }
        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    /**
     * Attempts to parse the user input for an Event task and returns an AddCommand if valid.
     *
     * @param otherWord The event description, start time, and end time provided by the user.
     * @return An AddCommand with an Event task if the input is valid, or an InvalidCommand if the format is incorrect.
     */
    private static Command attemptEventCommand(String otherWord) {
        String[] eventParts = otherWord.split(" /from | /to ");
        if (eventParts.length < 3) {
            return new InvalidCommand(EVENT, "event <description> /from <start> /to <end>");
        }
        return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    /**
     * Attempts to parse the user input for deleting a task and returns a DeleteCommand if valid.
     *
     * @param otherWord The task number provided by the user.
     * @return A DeleteCommand if the input is valid, or an InvalidCommand if the format is incorrect.
     */
    private static Command attemptDeleteCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand(DELETE, "delete <task number>");
        }
        try {
            int num = Integer.parseInt(otherWord) - 1;
            return new DeleteCommand(num);
        } catch (NumberFormatException e) {
            return new InvalidCommand(DELETE, "delete <task number>");
        }
    }

    /**
     * Attempts to parse the user input for marking a task as completed and returns a MarkCommand if valid.
     *
     * @param otherWord The task number provided by the user.
     * @return A MarkCommand if the input is valid, or an InvalidCommand if the format is incorrect.
     */
    private static Command attemptMarkCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            // throw TrackBotException.invalidFormat("mark", "mark <task number>");
            return new InvalidCommand(MARK, "mark <task number>");
        } else {
            try {
                int num = Integer.parseInt(otherWord) - 1;
                return new MarkCommand(num);
            } catch (NumberFormatException e) {
                return new InvalidCommand(MARK, "mark <task number>");
            }
        }
    }

    /**
     * Attempts to parse the user input for unmarking a task and returns an UnmarkCommand if valid.
     *
     * @param otherWord The task number provided by the user.
     * @return An UnmarkCommand if the input is valid, or an InvalidCommand if the format is incorrect.
     */
    private static Command attemptUnmarkCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand(UNMARK, "unmark <task number>");
        } else {
            try {
                int num = Integer.parseInt(otherWord) - 1;
                return new UnmarkCommand(num);
            } catch (NumberFormatException e) {
                return new InvalidCommand(UNMARK, "unmark <task number>");
            }
        }
    }

    /**
     * Attempts to parse the user input for finding tasks and returns a FindCommand if valid.
     *
     * @param otherWord The keyword provided by the user to search tasks.
     * @return A FindCommand if the input is valid, or an InvalidCommand if no keyword is provided.
     */
    private static Command attemptFindCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand(FIND, "find <task keyword>");
        }
        return new FindCommand(otherWord);
    }

    /**
     * Parses a task string from storage into a Task object.
     *
     * @param line The raw string representing a task in storage format.
     * @return The Task object corresponding to the input string, or null if the format is invalid.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.mark();
        }

        return task;
    }

    /**
     * Checks if the given date string matches the specified regex pattern.
     * @param regex The regular expression pattern to check against.
     * @param time The date string to be validated against the regex pattern.
     * @return true if the date string matches the regex pattern.
     */
    public static boolean checkDatePatternMatches(String regex, String time) {
        return Pattern.matches(regex, time);
    }

    /**
     * Validates and reformat a date/time string into a consistent format.
     *
     * @param inputDateTime The raw date/time string.
     * @return The reformatted date/time string.
     */
    public static String checkDateFormat(String inputDateTime) {
        Ui ui = new Ui();
        // Match dates in the format "YYYY-MM-DD"
        String regexYear = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        // Match dates in the format "MM-DD-YYYY"
        String regexMonth = "^(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-([0-9]{4})$";
        // Match dates in the format "DD-MM-YYYY"
        String regexDay1 = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([0-9]{4})$";
        // Match dates in the format "DD-MMM-YYYY"
        String regexDay2 = "^(0?[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD HH:MM"
        String regexTime = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]$";

        try {
            // Reformat as date only
            if (checkDatePatternMatches(regexYear, inputDateTime)) {
                return getFormattedDateString("yyyy-MM-dd", inputDateTime);
            }

            if (checkDatePatternMatches(regexMonth, inputDateTime)) {
                return getFormattedDateString("MM-dd-yyyy", inputDateTime);
            }

            if (checkDatePatternMatches(regexDay1, inputDateTime)) {
                return getFormattedDateString("dd-MM-yyyy", inputDateTime);
            }
            if (checkDatePatternMatches(regexDay2, inputDateTime)) {
                return getFormattedDateString("dd-MMM-yyyy", inputDateTime);
            }

            // Reformat as date and time only
            if (checkDatePatternMatches(regexTime, inputDateTime)) {
                return getFormattedDateTimeString("yyyy-MM-dd HH:mm", inputDateTime);
            }
        } catch (DateTimeParseException e) {
            return ui.getErrorMessage(e.getMessage());
        }
        return inputDateTime;
    }

    /**
     * Formats the given date-time string according to the specified pattern
     * and converts it to a standardized date-time format.
     * @param pattern The pattern in which the input string follows.
     * @param inputDateTime The input string to be formatted.
     * @return The formatted date-time string.
     */
    private static String getFormattedDateTimeString(String pattern, String inputDateTime) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        dateTime = LocalDateTime.parse(inputDateTime, formatter);
        inputDateTime = dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
        return inputDateTime;
    }

    /**
     * Formats the given date string according to the specified pattern
     * and converts it to a standardized date format.
     * @param pattern The pattern in which the input string follows.
     * @param inputDate The input string to be formatted.
     * @return The formatted date string.
     */
    private static String getFormattedDateString(String pattern, String inputDate) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        date = LocalDate.parse(inputDate, formatter);
        inputDate = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        return inputDate;
    }
}
