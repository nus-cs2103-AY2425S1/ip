package cypherchatbot.util;

import cypherchatbot.CypherException;
import cypherchatbot.command.ByeCommand;
import cypherchatbot.command.Command;
import cypherchatbot.command.DeadlineCommand;
import cypherchatbot.command.DeleteCommand;
import cypherchatbot.command.EventCommand;
import cypherchatbot.command.FindCommand;
import cypherchatbot.command.HelpCommand;
import cypherchatbot.command.ListCommand;
import cypherchatbot.command.MarkCommand;
import cypherchatbot.command.SortCommand;
import cypherchatbot.command.ToDoCommand;
import cypherchatbot.command.UnmarkCommand;


/**
 * The commander reader parses all the commands given by the user and
 * initializes a appropriate command based on user input
 */
public class CommandReader {

    private static enum Commands {
        LIST, TODO, EVENT, DEADLINE, MARK,
        UNMARK, BYE, HELP, DELETE, FIND, SORT
    }

    /**
     * Parses through the given input by user and instantiates a new Command Class
     *
     * @param input The string input given by the user
     * @return A Command Object depending on the input given by the user
     * @throws CypherException if an invalid input is given by the user
     * @throws NumberFormatException if an invalid input is given by the user for the mark/unmark/delete commands
     * @throws IllegalArgumentException if an unknown/invalid commands is given by the user
     */
    public static Command parse(String input) throws CypherException {
        String[] command = input.split(" ", 2);
        try {
            switch (Commands.valueOf(command[0].toUpperCase().trim())) {
            case LIST:
                return new ListCommand();
            case TODO:
                return new ToDoCommand(command);
            case DEADLINE:
                return new DeadlineCommand(command);
            case EVENT:
                return new EventCommand(command);
            case MARK:
                int markVal = Integer.parseInt(command[1]) - 1;
                return new MarkCommand(markVal);
            case UNMARK:
                int unmarkVal = Integer.parseInt(command[1]) - 1;
                return new UnmarkCommand(unmarkVal);
            case BYE:
                return new ByeCommand();
            case HELP:
                return new HelpCommand();
            case DELETE:
                int delVal = Integer.parseInt(command[1]) - 1;
                return new DeleteCommand(delVal);
            case FIND:
                return new FindCommand(command[1]);
            case SORT:
                return new SortCommand(command);
            default:
                throw new CypherException(String.format("\"%s\" is not a valid command. "
                            + "Type help in order to see the list of valid commands", command[0]));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CypherException("That is not a valid command. You need to enter an integer after that command. "
                    + "Type help in order to see the list of valid commands ");
        } catch (NumberFormatException exp) {
            throw new CypherException("That is not a valid command. You need to enter a valid integer. "
                    + "Type help in order to see the list of valid commands ");
        } catch (IllegalArgumentException exp) {
            throw new CypherException("That is not a valid command. "
                    + "Type help in order to see the list of valid commands ");

        }
    }
}
