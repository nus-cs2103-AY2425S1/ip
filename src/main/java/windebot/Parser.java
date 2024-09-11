package windebot;

import commands.AddDeadline;
import commands.AddEvent;
import commands.AddMark;
import commands.AddTodo;
import commands.AddUnmark;
import commands.ByeCommand;
import commands.ChangeCommand;
import commands.Command;
import commands.DateCommand;
import commands.ErrorCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.RemoveCommand;
import exceptions.UnsupportedCommandException;

/**
 * The Parser class interprets the user input and returns the corresponding Command object.
 * It acts as an interface between the user's input and the application's command execution.
 */

public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param input The user input string.
     * @return The Command object corresponding to the user's input.
     * @throws UnsupportedCommandException If the command is not recognized.
     */

    public static Command parse(String input) throws UnsupportedCommandException {
        String[] parts = input.split(" ", 2);
        SubCommandType command = getCommandType(parts[0]);
        String arguments = (parts.length > 1) ? parts[1] : "";
        Command commands = new ErrorCommand();
        try {
            switch (command) {
            case LIST:
                commands = new ListCommand();
                break;
            case DELETE:
                commands = new RemoveCommand();
                break;
            case MARK:
                commands = new AddMark();
                break;
            case UNMARK:
                commands = new AddUnmark();
                break;
            case TODO:
                commands = new AddTodo();
                break;
            case DEADLINE:
                commands = new AddDeadline();
                break;
            case EVENT:
                commands = new AddEvent();
                break;
            case DATE:
                commands = new DateCommand();
                break;
            case FIND:
                commands = new FindCommand();
                break;
            case BYE:
                commands = new ByeCommand();
                break;
            case CHANGE:
                commands = new ChangeCommand();
                break;
            case UNKNOWN:
                commands = new ErrorCommand();
                break;
            default:
                throw new UnsupportedCommandException("TYPE /HELP FOR HELP STOOPIDD");
            }
        } catch (UnsupportedCommandException uce) {
            System.out.println("TYPE /HELP FOR HELP STOOPIDD: " + uce.getMessage());
        }
        return commands;
    }

    /**
     * Determines the command type based on the user input.
     *
     * @param input The command keyword from the user input.
     * @return The subCommands enum corresponding to the command keyword.
     */

    private static SubCommandType getCommandType(String input) {
        if (input.startsWith("todo")) {
            return SubCommandType.TODO;
        } else if (input.startsWith("T")) {
            return SubCommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return SubCommandType.DEADLINE;
        } else if (input.startsWith("D")) {
            return SubCommandType.DEADLINE;
        } else if (input.startsWith("E")) {
            return SubCommandType.EVENT;
        } else if (input.startsWith("event")) {
            return SubCommandType.EVENT;
        } else if (input.equals("list")) {
            return SubCommandType.LIST;
        } else if (input.startsWith("delete")) {
            return SubCommandType.DELETE;
        } else if (input.equals("bye")) {
            return SubCommandType.BYE;
        } else if (input.startsWith("mark")) {
            return SubCommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return SubCommandType.UNMARK;
        } else if (input.startsWith("date")) {
            return SubCommandType.DATE;
        } else if (input.startsWith("find")) {
            return SubCommandType.FIND;
        } else if (input.startsWith("change")) {
            return SubCommandType.CHANGE;
        } else if (input.startsWith("cutoff")) {
            return SubCommandType.CHANGE;
        } else if (input.startsWith("start")) {
            return SubCommandType.CHANGE;
        } else if (input.startsWith("end")) {
            return SubCommandType.CHANGE;
        } else if (input.startsWith("action")) {
            return SubCommandType.CHANGE;
        } else {
            return SubCommandType.UNKNOWN;
        }
    }

    /**
     * Enumeration representing the possible command types.
     */

    enum SubCommandType {
        TODO, DEADLINE, EVENT, LIST, DELETE, BYE, MARK, UNMARK, DATE, FIND, CHANGE, UNKNOWN
    }
}
