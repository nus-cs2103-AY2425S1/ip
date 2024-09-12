package atlas.parser;

import java.time.DateTimeException;

import atlas.commands.AtlasCommand;
import atlas.commands.Command;
import atlas.commands.CommandManager;
import atlas.exceptions.AtlasException;

/**
 * Represents the parser class which contain methods to parse the command typed into the chatbot by the user.
 */
public class Parser {
    /**
     * Parses the command typed into the chatbot by the user.
     *
     * @param fullCommand The full command a user types into the chatbot through the command line.
     * @return Command The command to be executed by the chatbot.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    public static Command parse(String fullCommand) throws AtlasException {
        try {
            String[] commandsArray = fullCommand.split(" ");
            assert commandsArray.length > 0 : "Commands array should have at least 1 element";
            String command = commandsArray[0].toUpperCase();

            return switch (AtlasCommand.valueOf(command)) {
            case BYE -> CommandManager.getExitCommand();
            case LIST -> CommandManager.getListCommand(commandsArray);
            case MARK -> CommandManager.getMarkCommand(commandsArray);
            case UNMARK -> CommandManager.getUnmarkCommand(commandsArray);
            case TODO -> CommandManager.getTodoCommand(fullCommand);
            case DEADLINE -> CommandManager.getDeadlineCommand(fullCommand);
            case EVENT -> CommandManager.getEventCommand(fullCommand);
            case DELETE -> CommandManager.getDeleteCommand(commandsArray);
            case FIND -> CommandManager.getFindCommand(fullCommand);
            default -> throw new AtlasException("Unknown command.");
            };
        } catch (IllegalArgumentException e) {
            throw new AtlasException("Unknown command.");
        } catch (DateTimeException e) {
            throw new AtlasException("Invalid DateTime format. Please use yyyy-mm-dd HHmm");
        }
    }
}
