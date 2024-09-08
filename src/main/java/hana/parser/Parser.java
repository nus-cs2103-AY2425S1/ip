package hana.parser;

import hana.HanaException;
import hana.command.ByeCommand;
import hana.command.Command;
import hana.command.DeadlineCommand;
import hana.command.DeleteCommand;
import hana.command.EventCommand;
import hana.command.FindByDateCommand;
import hana.command.FindCommand;
import hana.command.GreetingsCommand;
import hana.command.HelpCommand;
import hana.command.ListCommand;
import hana.command.MarkCommand;
import hana.command.ToDoCommand;
import hana.command.UnmarkCommand;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND_BY_DATE = "findByDate";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_FIND_BY_KEY = "findByKey";
    private static final String COMMAND_HELLO = "hello";

    /**
     * Use input and return the command to execute.
     *
     * @param input
     * @return Command to execute.
     * @throws HanaException If input is not one of the available commands.
     */
    public static Command parse(String input) throws HanaException {
        String commandType = getCommandType(input);

        switch (commandType) {
        case COMMAND_MARK:
            return new MarkCommand(input);
        case COMMAND_UNMARK:
            return new UnmarkCommand(input);
        case COMMAND_TODO:
            return new ToDoCommand(input);
        case COMMAND_DEADLINE:
            return new DeadlineCommand(input);
        case COMMAND_EVENT:
            return new EventCommand(input);
        case COMMAND_DELETE:
            return new DeleteCommand(input);
        case COMMAND_BYE:
            return new ByeCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_FIND_BY_DATE:
            return new FindByDateCommand(input);
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_FIND_BY_KEY:
            return new FindCommand(input);
        case COMMAND_HELLO:
            return new GreetingsCommand();
        default:
            throw new HanaException("Unknown command! Use 'help' to see the list of available commands.");
        }
    }

    /**
     * Helper method to extract the command type from user input.
     *
     * @param input The user input command string.
     * @return A string representing the command type.
     */
    private static String getCommandType(String input) {
        return input.trim().split("\\s+")[0];
    }
}
