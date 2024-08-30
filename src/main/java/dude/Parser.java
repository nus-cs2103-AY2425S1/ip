package dude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeNullCommandException;

/**
 * Provides utility methods for parsing user input into commands, descriptions, and date-time objects.
 */
public class Parser {

    /**
     * Parses the input string and returns the corresponding CommandType.
     *
     * @param input The user's input string.
     * @return The CommandType that corresponds to the input command.
     * @throws DudeException If the input is empty or does not match any known command.
     */
    public static CommandType getCommand(String input) throws DudeException {
        if (input.isEmpty()) {
            throw new DudeNullCommandException();
        }

        String[] parsedInput = input.split(" ", 2);
        String commandString = parsedInput[0];
        CommandType command;

        try {
            command = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DudeInvalidCommandException();
        }

        return command;
    }

    /**
     * Extracts the description from the user's input string.
     *
     * @param input The user's input string.
     * @return The description part of the input, or an empty string if no description is provided.
     * @throws DudeException If the input is empty.
     */
    public static String getDescription(String input) throws DudeException {
        if (input.isEmpty()) {
            throw new DudeNullCommandException();
        }

        String[] parsedInput = input.split(" ", 2);

        return (parsedInput.length < 2 ? "" : parsedInput[1].strip());
    }

    /**
     * Converts a string representation of a date and time into a LocalDateTime object.
     * Date and time must be in specific format: "yyyy-MM-dd HH:mm"
     *
     * @param dateString The string representation of the date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DudeDateTimeFormatException If the input string cannot be parsed into a valid LocalTimeDate.
     */
    public static LocalDateTime stringToDateTime(String dateString) throws DudeDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DudeDateTimeFormatException();
        }

        return dateTime;
    }
}
