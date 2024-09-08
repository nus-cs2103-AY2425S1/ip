package skibidi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import skibidi.command.AbstractCommand;
import skibidi.command.AddCommand;
import skibidi.command.DeleteCommand;
import skibidi.command.FindCommand;
import skibidi.command.ListCommand;
import skibidi.command.MarkCommand;
import skibidi.command.UnmarkCommand;
import skibidi.task.Deadline;
import skibidi.task.Event;
import skibidi.task.Todo;

/**
 * Handles parsing of input commands.
 */
public class CommandParser {
    /**
     * Exception thrown when string command is invalid.
     */
    public static class CommandParseException extends Exception {
        public CommandParseException(String message) {
            super(message);
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
        FIND
    }

    /**
     * Check if command is exit command.
     */
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("exit");
    }

    /**
     * Parse given string command and returns command if succesful. Otherwise, an empty Optional is returned.
     */
    public AbstractCommand parseCommand(String line) throws CommandParseException {
        String[] args = line.split(" ", 2);
        if (args.length == 0) {
            throw new CommandParseException("NO COMMANDS GIVEN");
        }
        try {
            String[] cmdArgs;
            Commands command = Commands.valueOf(args[0].toUpperCase());
            switch (command) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(Integer.parseInt(args[1].strip()));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(args[1].strip()));
            case TODO:
                if (args.length != 2 || args[1].isEmpty()) {
                    throw new CommandParseException("COMMAND todo REQUIRES DESCRIPTION ARGUMENT");
                }
                Todo todo = new Todo(args[1].strip());
                String successMessage = String.format("ADDED TODO: %s\n", todo.toString());
                return new AddCommand(todo, successMessage);
            case DEADLINE:
                cmdArgs = args[1].split("/by");
                if (cmdArgs.length != 2) {
                    throw new CommandParseException("COMMAND deadline REQUIRES ARGUMENT /by");
                }
                Deadline deadline = new Deadline(
                        cmdArgs[0].strip(),
                        LocalDate.parse(cmdArgs[1].strip()));
                successMessage = String.format("ADDED DEADLINE: %s\n", deadline.toString());
                return new AddCommand(deadline, successMessage);
            case EVENT:
                // Assume order of arguments is always /from followed by /to
                cmdArgs = args[1].split("/from|/to");
                if (cmdArgs.length != 3) {
                    throw new CommandParseException("COMMAND event REQUIRES ARGUMENTS /from AND /to");
                }
                Event event = new Event(
                        cmdArgs[0].strip(),
                        LocalDate.parse(cmdArgs[1].strip()),
                        LocalDate.parse(cmdArgs[2].strip()));
                successMessage = String.format("ADDED EVENT: %s\n", event.toString());
                return new AddCommand(event, successMessage);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(args[1].strip()));
            case FIND:
                if (args.length != 2 || args[1].isEmpty()) {
                    throw new CommandParseException("COMMAND find REQUIRES A SEARCH QUERY");
                }
                return new FindCommand(args[1].strip());
            default:
                throw new CommandParseException("UNKNOWN COMMAND GIVEN");
            }
        } catch (NumberFormatException err) {
            throw new CommandParseException(String.format("ERROR: INVALID NUMBER GIVEN FOR COMMAND: %s", args[0]));
        } catch (IllegalArgumentException err) {
            throw new CommandParseException(String.format("ERROR: INVALID COMMAND GIVEN %s", args[0]));
        } catch (DateTimeParseException err) {
            throw new CommandParseException("ERROR: COULD NOT PARSE DATE ARGUMENT");
        }
    }
}
