package sigma;

import sigma.command.*;
import sigma.exception.SigmaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that parses user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * @param command User input.
     * @return Corresponding command.
     */
    public static Commands parse(String command) {
        String[] split = command.split(" ", 2);
        String cmd = split[0]; // Convert input to uppercase to match enum
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
        default:
            return new UnrecognisedCommand(split);
        }
    }

    /**
     * Parses the date and time input and returns a LocalDateTime object.
     * @param timing Date and time input.
     * @return LocalDateTime object.
     * @throws SigmaException If the date and time input is invalid.
     */
    public static LocalDateTime dateTimeParse(String timing) throws SigmaException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new SigmaException("What the sigma? Invalid date format! " +
                    "Write the date in the format YYYY-MM-DD HHmm!");
        }
        return dateTime;
    }
}
