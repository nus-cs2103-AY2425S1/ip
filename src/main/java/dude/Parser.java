package dude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import dude.exception.DudeDateTimeFormatException;
import dude.exception.DudeException;
import dude.exception.DudeInvalidCommandException;
import dude.exception.DudeInvalidDefineException;
import dude.exception.DudeNullCommandException;
import dude.exception.DudeShortcutDeleteException;

/**
 * Provides utility methods for parsing user input into commands, descriptions, and date-time objects.
 */
public class Parser {
    private HashMap<String, CommandType> shortcutMap;

    public Parser(HashMap<String, CommandType> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Parses the input string and returns the corresponding CommandType.
     *
     * @param input The user's input string.
     * @return The CommandType that corresponds to the input command.
     * @throws DudeException If the input is empty or does not match any known command.
     */
    public CommandType getCommand(String input) throws DudeException {
        if (input.isEmpty()) {
            throw new DudeNullCommandException();
        }

        String[] parsedInput = input.split(" ", 2);
        String commandString = parsedInput[0];
        CommandType command;

        if (shortcutMap.containsKey(commandString)) {
            command = shortcutMap.get(commandString);

            return command;
        }

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

    public HashMap<String, CommandType> getShortcutMap() {
        return shortcutMap;
    }

    public CommandType defineShortcut(String shortcut, String command) throws DudeInvalidDefineException {
        CommandType commandType;

        try {
            CommandType.valueOf(shortcut.toUpperCase());
            throw new DudeInvalidDefineException();
        } catch (IllegalArgumentException e) {
            // shortcut is not a valid CommandType
        }

        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e1) {
            throw new DudeInvalidDefineException(command);
        }

        shortcutMap.put(shortcut, commandType);
        return commandType;
    }

    public void deleteShortcut(String shortcut) throws DudeException {
        try {
            CommandType.valueOf(shortcut.toUpperCase());
            throw new DudeShortcutDeleteException();
        } catch (IllegalArgumentException e) {
            // shortcut is not a valid CommandType
        }

        if (shortcutMap.remove(shortcut) == null) {
            throw new DudeShortcutDeleteException(shortcut);
        }
    }
}
