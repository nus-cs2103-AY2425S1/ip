package neuro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import neuro.command.AddCommand;
import neuro.command.Command;
import neuro.command.DeleteCommand;
import neuro.command.ExitCommand;
import neuro.command.FindCommand;
import neuro.command.ListCommand;
import neuro.command.MarkCommand;
import neuro.command.UnmarkCommand;

/**
 * The {@code Parser} class handles the parsing of user input strings.
 * It provides methods to parse for Neuro's commands, parse strings for
 * date and time format and helper methods for Deadline and Event.
 */
public class Parser {
    /**
     * Parses the user input string for the command to give Neuro.
     *
     * @param userCommand the user's input string to be parsed
     * @return the correct Command based off the user's input
     */
    public static Command parse(String userCommand) {
        if (userCommand.equals("bye")) {
            return new ExitCommand();
        } else if (userCommand.equals("list")) {
            return new ListCommand();
        } else if (userCommand.startsWith("mark")) {
            return new MarkCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("unmark")) {
            return new UnmarkCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("delete")) {
            return new DeleteCommand(getIndexFromCommand(userCommand));
        } else if (userCommand.startsWith("find")) {
            return new FindCommand(userCommand.substring("find".length()));
        } else {
            return new AddCommand(userCommand.split(" "));
        }
    }

    /**
     * Parses strings for a date and time format.
     *
     * @param dateTimeStr the input string that contains a date and time format
     * @return a {@code LocalDateTime} object which contains data of the date and time
     * @throws IllegalArgumentException when input string does not follow any expected format
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws IllegalArgumentException {
        DateTimeFormatter[] dateTimeFormatters = getDateTimeFormatters();
        DateTimeFormatter[] dateFormatters = getDateFormatters();

        // Check if dateTimeStr follows any DateTime formats
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        // Check if dateTimeStr follows any Date formats
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(dateTimeStr, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new IllegalArgumentException("Invalid date string!");
    }

    private static DateTimeFormatter[] getDateFormatters() {
        return new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("MMM d yyyy")
        };
    }

    private static DateTimeFormatter[] getDateTimeFormatters() {
        return new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("MMM d yyyy h a"),
                DateTimeFormatter.ofPattern("MMM d yyyy ha")
        };
    }

    /**
     * Gets the index of '/by' in the command components array
     *
     * @param commandComponents the array containing components of the user's input command
     * @return the index of '/by' in the array
     */
    public static int getDeadlineByIndex(String[] commandComponents) {
        int byIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        // Missing /by
        if (byIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/by' input for deadline."
                    + "Try updating the command like 'deadline finish homework /by Mon 2359'.");
        }
        return byIndex;
    }

    /**
     * Gets the index of '/from' in the command components array
     *
     * @param commandComponents the array containing components of the user's input command
     * @return the index of '/from' in the array
     */
    public static int getEventFromIndex(String[] commandComponents) {
        int fromIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/from")) {
                fromIndex = i;
                break;
            }
        }

        // Missing /from
        if (fromIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/from' input for event."
                    + "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
        }
        return fromIndex;
    }

    /**
     * Gets the index of '/to' in the command components array
     *
     * @param commandComponents the array containing components of the user's input command
     * @return the index of '/to' in the array
     */
    public static int getEventToIndex(String[] commandComponents) {
        int toIndex = -1;
        for (int i = 1; i < commandComponents.length; i++) {
            if (commandComponents[i].equals("/to")) {
                toIndex = i;
                break;
            }
        }

        // Missing /to
        if (toIndex < 0) {
            throw new IllegalArgumentException("UH OH! The command given is missing the '/to' input for event."
                    + "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
        }
        return toIndex;
    }

    private static int getIndexFromCommand(String userCommand) {
        String[] commandComponents = userCommand.split(" ");

        try {
            return Integer.parseInt(commandComponents[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return -1;
        }
    }
}
