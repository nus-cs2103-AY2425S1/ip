package papadom;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.tasks.Deadline;
import papadom.tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class to handle parsing of user input into tasks.
 */
public class Parser {
    /**
     * Creates a Deadline task from the provided details.
     *
     * @param details The string containing the description and deadline date.
     * @return A Deadline task.
     * @throws IncorrectTaskInputFormatException If the input format is incorrect.
     */
    public Deadline deadlineTaskCreator(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /by ");
        if (parts[0] == "" || parts.length == 1) {
            throw new IncorrectTaskInputFormatException();
        }
        try {
            // Determine if the input includes a time
            if (parts[1].contains(" ")) {
                // If it includes a time, parse it as LocalDateTime
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1], dateTimeFormatter);
                return new Deadline(parts[0], dateTime);
            } else {
                // If it doesn't include a time, parse it as LocalDate
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    public Event eventTaskCreator(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /from | /to ");
        if (parts[0] == "" || parts.length <= 2) {
            throw new IncorrectTaskInputFormatException();
        }
        return new Event(parts[0], parts[1], parts[2]);
    }

    public String findKeyword(String command) throws IncorrectTaskInputFormatException {
        // Split the command by space and store the words in an array
        String[] words = command.split(" ");

        // Check if the command starts with "find" and has a keyword following it
        if (words.length > 1 && words[0].equals("find")) {
            return words[1];  // Return the keyword after "find"
        } else {
            throw new IncorrectTaskInputFormatException();
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The string containing the date and time.
     * @return A LocalDateTime object, or null if parsing fails.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            // handle the exception or return a default
            return null;
        }
    }
}
