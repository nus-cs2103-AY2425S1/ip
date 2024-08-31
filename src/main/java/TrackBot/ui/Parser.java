package TrackBot.ui;


import TrackBot.TrackBotException;
import TrackBot.commands.AddCommand;
import TrackBot.commands.Command;
import TrackBot.commands.DeleteCommand;
import TrackBot.commands.ExitCommand;
import TrackBot.commands.ListCommand;
import TrackBot.commands.MarkCommand;
import TrackBot.commands.UnmarkCommand;
import TrackBot.task.Deadline;
import TrackBot.task.Event;
import TrackBot.task.Task;
import TrackBot.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Parses user inputs and translate them into commands to be executed by TrackBot.
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
            if (!otherWord.isEmpty()) {
                throw TrackBotException.invalidFormat("list", "list");
            }
            return new ListCommand();
        case "todo":
            if (otherWord.isEmpty()) {
                throw TrackBotException.invalidFormat("todo", "todo <task description>");
            }
            return new AddCommand(new ToDo(otherWord));
        case "deadline":
            String[] deadlineParts = otherWord.split(" /by ");
            if (deadlineParts.length < 2) {
                throw TrackBotException.invalidFormat("deadline", "deadline <task description> /by <date/time>");
            }
            return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
        case "event":
            String[] eventParts = otherWord.split(" /from | /to ");
            if (eventParts.length < 3) {
                throw TrackBotException.invalidFormat("event", "event <description> /from <start> /to <end>");
            }
            return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
        case "delete":
            if (otherWord.isEmpty()) {
                throw TrackBotException.invalidFormat("delete", "delete <task number>");
            }
            try {
                int num = Integer.parseInt(otherWord) - 1;
                return new DeleteCommand(num);
            } catch (NumberFormatException e) {
                throw TrackBotException.invalidFormat("delete", "delete <task number>");
            }
        case "mark":
            if (otherWord.isEmpty()) {
                throw TrackBotException.invalidFormat("mark", "mark <task number>");
            } else {
                try {
                    int num = Integer.parseInt(otherWord) - 1;
                    return new MarkCommand(num);
                } catch (NumberFormatException e) {
                    throw TrackBotException.invalidFormat("mark", "mark <task number>");
                }
            }
        case "unmark":
            if (otherWord.isEmpty()) {
                throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
            } else {
                try {
                    int num = Integer.parseInt(otherWord) - 1;
                    return new UnmarkCommand(num);
                } catch (NumberFormatException e) {
                    throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
                }
            }
        case "bye":
            return new ExitCommand();
        default:
            throw new TrackBotException("Sorry, I did not understand that command.");
    }
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
     * Validates and reformats a date/time string into a consistent format.
     *
     * @param time The raw date/time string.
     * @return The reformatted date/time string.
     */
    public static String checkDateFormat(String time) {
        LocalDate date;
        LocalDateTime dateTime;
        // Match dates in the format "YYYY-MM-DD"
        String regex_y = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        // Match dates in the format "MM-DD-YYYY"
        String regex_m = "^(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-([0-9]{4})$";
        // Match dates in the format "DD-MM-YYYY"
        String regex_d1 = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([0-9]{4})$";
        // Match dates in the format "DD-MMM-YYYY"
        String regex_d2 = "^(0?[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD HH:MM"
        String regex_t1 = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]$";

        try {
            // Reformat as date only
            if (Pattern.matches(regex_y, time)) {
                date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            if (Pattern.matches(regex_m, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            if (Pattern.matches(regex_d1, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }
            if (Pattern.matches(regex_d2, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                date = LocalDate.parse(time, formatter);
                time = date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
                return time;
            }

            // Reformat as date and time only
            if (Pattern.matches(regex_t1, time)) {
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
