package mittens.parser;

import mittens.commands.AddCommand;
import mittens.commands.Command;
import mittens.commands.DeleteCommand;
import mittens.commands.ExitCommand;
import mittens.commands.FindCommand;
import mittens.commands.ListCommand;
import mittens.commands.MarkCommand;
import mittens.commands.UnmarkCommand;
import mittens.task.Deadline;
import mittens.task.Event;
import mittens.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser for commands.
 */
public class CommandParser {

    /**
     * Parses the input and returns the corresponding command.
     * If no match with any command format is found, a BadInputException is thrown.
     * 
     * @param input The user input to parse
     * @return The corresponding Command object
     * @throws BadInputException If the input does not match any known command format
     */
    public Command parse(String input) throws BadInputException {
        RawCommandElements elements = new RawCommandElements(input);

        return switch (elements.getCommand()) {
            case "bye" -> new ExitCommand(elements);
            case "list" -> new ListCommand(elements);
            case "mark" -> new MarkCommand(elements);
            case "unmark" -> new UnmarkCommand(elements);
            case "delete" -> new DeleteCommand(elements);
            case "find" -> new FindCommand(elements);
            case "todo", "deadline", "event" -> new AddCommand(elements);
            default -> throw new BadInputException("'%s' is not a known command".formatted(input));
        };
    }
}
