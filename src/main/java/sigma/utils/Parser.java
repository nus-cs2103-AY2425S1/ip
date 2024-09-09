package sigma.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sigma.command.ByeCommand;
import sigma.command.Command;
import sigma.command.DeadlineCommand;
import sigma.command.DeleteCommand;
import sigma.command.EventCommand;
import sigma.command.FindCommand;
import sigma.command.HelpCommand;
import sigma.command.ListCommand;
import sigma.command.MarkCommand;
import sigma.command.TodoCommand;
import sigma.command.UnmarkCommand;
import sigma.command.UnrecognisedCommand;
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
    public static Command parse(String command) {
        String[] split = command.split(" ", 2);
        assert split != null : "Split cannot be null";
        assert split.length > 0 : "Split length cannot be 0";
        String cmd = split[0].toLowerCase();
        switch (cmd) {
        case "todo":
            return new TodoCommand(split);
        case "deadline":
            return new DeadlineCommand(split);
        case "event":
            return new EventCommand(split);
        case "list":
            return new ListCommand(split);
        case "mark":
            return new MarkCommand(split);
        case "unmark":
            return new UnmarkCommand(split);
        case "delete":
            return new DeleteCommand(split);
        case "bye":
            return new ByeCommand(split);
        case "find":
            return new FindCommand(split);
        case "help":
            return new HelpCommand(split);
        default:
            return new UnrecognisedCommand(split);
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
