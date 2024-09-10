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
import cypherchatbot.command.ToDoCommand;
import cypherchatbot.command.UnmarkCommand;

public class CommandReader {

    private static enum Commands {
        LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, BYE, HELP, DELETE, FIND
    }

    /**
     * Parses through the given input by user and instantiates a new Command Class
     *
     * @param input The string input given by the user
     * @return A Command Object depending on the input given by the user
     * @throws CypherException if an invalid input is given by the user
     */
    public static Command parse(String input) throws CypherException {
        String[] command = input.split(" ", 2);
        try {
            switch (Commands.valueOf(command[0].toUpperCase())) {
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
            default:
                throw new CypherException(String.format("\"%s\" is not a valid command. "
                            + "Type help in order to see the list of valid commands"
                                + "(This feature is still under construction)\n", command[0]));
            }
        } catch (NumberFormatException exp) {
            throw new CypherException("That is not a valid command. You need to enter a valid integer. "
                    + "Type help in order to see the list of valid commands "
                        + "(This feature is still under construction)");
        } catch (IllegalArgumentException exp) {
            throw new CypherException("That is not a valid command. "
                    + "Type help in order to see the list of valid commands "
                        + "(This feature is still under construction)");
        }
    }
}
