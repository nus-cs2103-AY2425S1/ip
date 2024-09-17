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

        switch (commandWord) {
        case "list":
            return attemptListCommand(otherWord);
        case "todo":
            return attemptToDoCommand(otherWord);
        case "deadline":
            return attemptDeadlineCommand(otherWord);
        case "event":
            return attemptEventCommand(otherWord);
        case "delete":
            return attemptDeleteCommand(otherWord);
        case "mark":
            return attemptMarkCommand(otherWord);
        case "unmark":
            return attemptUnmarkCommand(otherWord);
        case "find":
            return attemptFindCommand(otherWord);
        case "bye":
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static Command attemptListCommand(String otherWord) {
        if (!otherWord.isEmpty()) {
            return new InvalidCommand("list", "list");
        }
        return new ListCommand();
    }

    private static Command attemptToDoCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand("todo", "todo <task description>");
        }
        return new AddCommand(new ToDo(otherWord));
    }

    private static Command attemptDeadlineCommand(String otherWord) {
        String[] deadlineParts = otherWord.split(" /by ");
        if (deadlineParts.length < 2) {
            return new InvalidCommand("deadline", "deadline <task description> /by <date/time>");
        }
        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
    }


    private static Command attemptEventCommand(String otherWord) {
        String[] eventParts = otherWord.split(" /from | /to ");
        if (eventParts.length < 3) {
            return new InvalidCommand("event", "event <description> /from <start> /to <end>");
        }
        return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    private static Command attemptDeleteCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand("delete", "delete <task number>");
        }
        try {
            int num = Integer.parseInt(otherWord) - 1;
            return new DeleteCommand(num);
        } catch (NumberFormatException e) {
            return new InvalidCommand("delete", "delete <task number>");
        }
    }
    private static Command attemptMarkCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            // throw TrackBotException.invalidFormat("mark", "mark <task number>");
            return new InvalidCommand("mark", "mark <task number>");
        } else {
            try {
                int num = Integer.parseInt(otherWord) - 1;
                return new MarkCommand(num);
            } catch (NumberFormatException e) {
                return new InvalidCommand("mark", "mark <task number>");
            }
        }
    }
    private static Command attemptUnmarkCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            // throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
            return new InvalidCommand("unmark", "unmark <task number>");
        } else {
            try {
                int num = Integer.parseInt(otherWord) - 1;
                return new UnmarkCommand(num);
            } catch (NumberFormatException e) {
                return new InvalidCommand("unmark", "unmark <task number>");
            }
        }
    }

    private static Command attemptFindCommand(String otherWord) {
        if (otherWord.isEmpty()) {
            return new InvalidCommand("find", "find <task keyword>");
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
     * Validates and reformat a date/time string into a consistent format.
     *
     * @param time The raw date/time string.
     * @return The reformatted date/time string.
     */
    public static String checkDateFormat(String time) {
        LocalDate date;
        LocalDateTime dateTime;
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
            if (Pattern.matches(regexYear, time)) {
                date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            if (Pattern.matches(regexMonth, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            if (Pattern.matches(regexDay1, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }
            if (Pattern.matches(regexDay2, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            // Reformat as date and time only
            if (Pattern.matches(regexTime, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                dateTime = LocalDateTime.parse(time, formatter);
                time = dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
                return time;
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return time;
    }
}
