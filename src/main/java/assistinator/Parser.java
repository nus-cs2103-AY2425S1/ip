package assistinator;

import assistinator.commands.ByeCommand;
import assistinator.commands.Command;
import assistinator.commands.DeadlineCommand;
import assistinator.commands.DeleteCommand;
import assistinator.commands.EventCommand;
import assistinator.commands.FindCommand;
import assistinator.commands.InvalidCommand;
import assistinator.commands.ListCommand;
import assistinator.commands.MarkCommand;
import assistinator.commands.TodoCommand;
import assistinator.commands.UnmarkCommand;

/**
 * Parses input and creates respective command.
 */
public class Parser {
    /**
     * Parses command from user input.
     * @param input User input from UI.
     * @return Command to be used by.
     */
    public Command parseCommand(String input) {
        CommandWord commandType = CommandWord.parseCommandWord(input.split(" ")[0].toUpperCase());
        return switch (commandType) {
        case LIST -> new ListCommand(input);
        case TODO -> new TodoCommand(input);
        case DEADLINE -> new DeadlineCommand(input);
        case EVENT -> new EventCommand(input);
        case MARK -> new MarkCommand(input);
        case UNMARK -> new UnmarkCommand(input);
        case DELETE -> new DeleteCommand(input);
        case FIND -> new FindCommand(input);
        case BYE -> new ByeCommand(input);
        default -> new InvalidCommand(input);
        };
    }
}
