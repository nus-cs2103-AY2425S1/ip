package WindeBot;

import Commands.Command;
import Commands.ListCommand;
import Commands.RemoveTask;
import Commands.AddMark;
import Commands.AddUnmark;
import Commands.AddTodo;
import Commands.AddDeadline;
import Commands.AddEvent;
import Commands.TaskDateCommand;
import Commands.FindCommand;
import Commands.ByeCommand;
import Commands.ErrorCommand;
import Exceptions.UnsupportedCommandException;

public class Parser {

    public static Command parse(String input) throws UnsupportedCommandException {
        String[] parts = input.split(" ", 2);
        subCommands command = getCommandType(parts[0]);
        String arguments = (parts.length > 1) ? parts[1] : "";
        Command commands = new ErrorCommand();
        try {
            switch (command) {
            case LIST:
                commands = new ListCommand();
                break;
            case DELETE:
                commands = new RemoveTask();
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
                commands = new TaskDateCommand();
                break;
            case FIND:
                commands = new FindCommand();
                break;
            case BYE:
                commands = new ByeCommand();
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

    private static subCommands getCommandType(String input) {
        if (input.startsWith("todo")) {
            return subCommands.TODO;
        } else if (input.startsWith("T")) {
            return subCommands.TODO;
        } else if (input.startsWith("deadline")) {
            return subCommands.DEADLINE;
        } else if (input.startsWith("D")) {
            return subCommands.DEADLINE;
        } else if (input.startsWith("E")) {
            return subCommands.EVENT;
        } else if (input.startsWith("event")) {
            return subCommands.EVENT;
        } else if (input.equals("list")) {
            return subCommands.LIST;
        } else if (input.startsWith("delete")) {
            return subCommands.DELETE;
        } else if (input.equals("bye")) {
            return subCommands.BYE;
        } else if (input.startsWith("mark")) {
            return subCommands.MARK;
        } else if (input.startsWith("unmark")) {
            return subCommands.UNMARK;
        } else if (input.startsWith("date")) {
            return subCommands.DATE;
        } else if (input.startsWith("find")) {
            return subCommands.FIND;
        } else {
            return subCommands.UNKNOWN;
        }
    }

    enum subCommands {
        TODO, DEADLINE, EVENT, LIST, DELETE, BYE, MARK, UNMARK, DATE, FIND, UNKNOWN
    }
}
