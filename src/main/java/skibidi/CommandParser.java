package skibidi;

import skibidi.command.AbstractCommand;
import skibidi.command.AddCommand;
import skibidi.command.DeleteCommand;
import skibidi.command.FindCommand;
import skibidi.command.ListCommand;
import skibidi.command.MarkCommand;
import skibidi.command.SetPriorityCommand;
import skibidi.command.UnmarkCommand;
import skibidi.task.AbstractTask.TaskValidationException;
import skibidi.task.Deadline;
import skibidi.task.Event;
import skibidi.task.Todo;

/** Handles parsing of input commands. */
public class CommandParser {
    /** Exception thrown when string command is invalid. */
    public static class CommandParseException extends Exception {
        public CommandParseException(String message) {
            super("COMMAND ERROR: " + message);
        }
    }

    enum Commands {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        PRIORITY
    }

    /** Check if command is exit command. */
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("exit");
    }

    /** Parse given string command and returns command if succesful. */
    public AbstractCommand parseCommand(String line) throws CommandParseException, TaskValidationException {
        String[] args = line.split(" ", 2);
        if (args.length == 0) {
            throw new CommandParseException("NO COMMANDS GIVEN");
        }
        try {
            Commands command = Commands.valueOf(args[0].toUpperCase());
            switch (command) {
            case LIST:
                if (args.length != 1) {
                    throw new CommandParseException("COMMAND list SHOULD BE RUN WITH 0 ARGUMENTS");
                }
                return new ListCommand();
            case MARK:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND mark REQUIRES A SINGLE NUMBER ARGUMENT");
                }
                return new MarkCommand(Integer.parseInt(args[1].strip()));
            case UNMARK:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND unmark REQUIRES A SINGLE NUMBER ARGUMENT");
                }
                return new UnmarkCommand(Integer.parseInt(args[1].strip()));
            case TODO:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND todo REQUIRES A SINGLE DESCRIPTION ARGUMENT");
                }
                Todo todo = Todo.validateThenCreate(args[1]);
                String successMessage = String.format("ADDED TODO: %s\n", todo.toString());
                return new AddCommand(todo, successMessage);
            case DEADLINE:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND deadline REQUIRES  ARGUMENTS");
                }
                Deadline deadline = Deadline.validateThenCreate(args[1].split("/by"));
                successMessage = String.format("ADDED DEADLINE: %s\n", deadline.toString());
                return new AddCommand(deadline, successMessage);
            case EVENT:
                if (args.length == 1) {
                    throw new CommandParseException("COMMAND event REQUIRES /by ARGUMENTS");
                }
                Event event = Event.validateThenCreate(args[1].split("/from|/to"));
                successMessage = String.format("ADDED EVENT: %s\n", event.toString());
                return new AddCommand(event, successMessage);
            case DELETE:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND delete REQUIRES A SINGLE NUMBER ARGUMENT");
                }
                return new DeleteCommand(Integer.parseInt(args[1].strip()));
            case FIND:
                if (args.length != 2 || args[1].isEmpty()) {
                    throw new CommandParseException("COMMAND find REQUIRES A SEARCH QUERY");
                }
                return new FindCommand(args[1].strip());
            case PRIORITY:
                if (args.length != 2) {
                    throw new CommandParseException("COMMAND priority REQUIRES 2 NUMBER ARGUMENTS");
                }
                String[] cmdArgs = args[1].split(" ");
                if (cmdArgs.length != 2) {
                    throw new CommandParseException("COMMAND priority REQUIRES 2 NUMBER ARGUMENTS");
                }
                return new SetPriorityCommand(
                        Integer.parseInt(cmdArgs[0].strip()),
                        Integer.parseInt(cmdArgs[1].strip()));
            default:
                throw new CommandParseException(String.format("INVALID COMMAND %s", args[0]));
            }
        } catch (NumberFormatException e) {
            throw new CommandParseException(String.format("ERROR: INVALID NUMBER GIVEN FOR COMMAND: %s", args[0]));
        } catch (IllegalArgumentException e) {
            throw new CommandParseException(String.format("INVALID COMMAND %s", args[0]));
        }
    }
}
