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
        SubCommandType command = getCommandType(parts[0]);
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
        } else {
            return SubCommandType.UNKNOWN;
        }
    }

    enum SubCommandType {
        TODO, DEADLINE, EVENT, LIST, DELETE, BYE, MARK, UNMARK, DATE, FIND, UNKNOWN
    }
}
