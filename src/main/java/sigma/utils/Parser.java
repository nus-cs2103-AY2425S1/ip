package sigma.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sigma.exception.SigmaException;

/**
 * Represents a parser that parses user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param command User input.
     * @return Corresponding command.
     */
    public static sigma.command.Command parse(String command) {
        String[] commandArray = command.split(" ", 2);
        assert commandArray != null : "Command array cannot be null";
        assert commandArray.length > 0 : "Command array length cannot be 0";
        String cmd = commandArray[0].toLowerCase();
        switch (cmd) {
        case "todo":
            return new sigma.command.TodoCommand(commandArray);
        case "deadline":
            return new sigma.command.DeadlineCommand(commandArray);
        case "event":
            return new sigma.command.EventCommand(commandArray);
        case "list":
            return new sigma.command.ListCommand(commandArray);
        case "mark":
            return new sigma.command.MarkCommand(commandArray);
        case "unmark":
            return new sigma.command.UnmarkCommand(commandArray);
        case "delete":
            return new sigma.command.DeleteCommand(commandArray);
        case "bye":
            return new sigma.command.ByeCommand(commandArray);
        case "find":
            return new sigma.command.FindCommand(commandArray);
        case "help":
            return new sigma.command.HelpCommand(commandArray);
        case "sort":
            return new sigma.command.SortCommand(commandArray);
        default:
            return new sigma.command.UnrecognisedCommand(commandArray);
        }
    }

    /**
     * Parses the date and time input and returns a LocalDateTime object.
     *
     * @param timing Date and time input.
     * @return LocalDateTime object.
     * @throws SigmaException If the date and time input is invalid.
     */
    public static LocalDateTime parseLocalDateTime(String timing) throws SigmaException {
        assert timing != null : "Timing cannot be null";
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new SigmaException("What the sigma? Invalid date format! "
                    + "Write the date in the format YYYY-MM-DD HHmm!");
        }
        return dateTime;
    }

}
