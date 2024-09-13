package papadom.utils;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.tasks.Deadline;
import papadom.tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Parser class to handle parsing of user input into tasks.
 */
public class Parser {
    public static final String DATETIME_FORMAT_WITH_TIME = "yyyy-MM-dd HH-mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Creates a Deadline task from the provided details.
     *
     * @param details The string containing the description and deadline date.
     * @return A Deadline task.
     * @throws IncorrectTaskInputFormatException If the input format is incorrect.
     */
    public Deadline createDeadlineTask(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /by ");
        if (Objects.equals(parts[0], "") || parts.length == 1) {
            throw new IncorrectTaskInputFormatException();
        }
        try {
            // Determine if the input includes a time
            if (parts[1].contains(" ")) {
                // If it includes a time, parse it as LocalDateTime
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_WITH_TIME);
                LocalDateTime dateTime = LocalDateTime.parse(parts[1], dateTimeFormatter);
                return new Deadline(parts[0], dateTime);
            } else {
                // If it doesn't include a time, parse it as LocalDate
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate date = LocalDate.parse(parts[1], dateFormatter);
                return new Deadline(parts[0], date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectTaskInputFormatException(); // Throw custom exception if parsing fails
        }
    }

    /**
     * Creates an Event task from the provided details.
     *
     * @param details The string containing the description, start time, and end time.
     * @return An Event task.
     * @throws IncorrectTaskInputFormatException If the input format is incorrect.
     */
    public Event createEventTask(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /from | /to ");
        boolean isPartsLengthOne = parts.length == 1;
        if (parts[0].isEmpty() || isPartsLengthOne) {
            throw new IncorrectTaskInputFormatException();
        }
        return new Event(parts[0], parts[1], parts[2]);
    }

    public String findKeyword(String command) throws IncorrectTaskInputFormatException {
        // Split into two parts: "find" and the rest of the input
        String[] words = command.split(" ", 2);

        // Check if the command starts with "find" and has a keyword following it
        if (words.length > 1 && words[0].equals("find")) {
            return words[1]; // Return the keyword after "find"
        } else {
            throw new IncorrectTaskInputFormatException();
        }
    }
}
