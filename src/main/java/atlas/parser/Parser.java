package atlas.parser;

import java.time.DateTimeException;

import atlas.commands.AtlasCommand;
import atlas.commands.AtlasSimpleCommand;
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

            // Modifies the commandsArray if it contains a short form / simple command
            AtlasCommand command = Parser.parseCommandsArray(commandsArray);
            String modifiedCommand = String.join(" ", commandsArray);

            return switch (command) {
            case BYE -> CommandManager.getExitCommand();
            case LIST -> CommandManager.getListCommand(commandsArray);
            case MARK -> CommandManager.getMarkCommand(commandsArray);
            case UNMARK -> CommandManager.getUnmarkCommand(commandsArray);
            case DELETE -> CommandManager.getDeleteCommand(commandsArray);
            case FIND -> CommandManager.getFindCommand(modifiedCommand);
            case TODO -> CommandManager.getTodoCommand(modifiedCommand);
            case DEADLINE -> CommandManager.getDeadlineCommand(modifiedCommand);
            case EVENT -> CommandManager.getEventCommand(modifiedCommand);
            case HELP -> CommandManager.getHelpCommand();
            default -> throw new AtlasException("Unknown command.");
            };
        } catch (IllegalArgumentException e) {
            throw new AtlasException("Unknown command.");
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            throw new AtlasException("Invalid DateTime format. Please use yyyy-mm-dd HHmm");
        }
    }

    /**
     * Fetches the long form command if there is a short form command used. It also modifies the commandsArray
     *     to contain the long command word instead of the short command char.
     *
     * @param commandsArray The array of commands that consist all the words the user has typed in the chatbot as an
     *     entire command.
     * @return AtlasCommand The command that the user would like the chatbot to execute.
     * @throws IllegalArgumentException The error thrown when a user types in a command that does not exist.
     */
    public static AtlasCommand parseCommandsArray(String[] commandsArray) throws IllegalArgumentException {
        AtlasCommand command;
        String commandString = commandsArray[0].toUpperCase();
        if (commandString.length() == 1) {
            AtlasSimpleCommand simpleCommand = AtlasSimpleCommand.valueOf(commandString);
            command = CommandManager.getFullCommandFromSimpleCommand(simpleCommand);
            commandsArray[0] = command.toString().toLowerCase();
        } else {
            command = AtlasCommand.valueOf(commandString);
        }

        return command;
    }
}
