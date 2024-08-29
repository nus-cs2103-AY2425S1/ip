package skibidi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import skibidi.command.AbstractCommand;
import skibidi.command.AddCommand;
import skibidi.command.DeleteCommand;
import skibidi.command.ListCommand;
import skibidi.command.MarkCommand;
import skibidi.command.UnmarkCommand;
import skibidi.task.Deadline;
import skibidi.task.Event;
import skibidi.task.Todo;

public class CommandParser {
    static class CommandParseException extends Exception {
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
        DELETE
    }

    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("exit");
    }

    public Optional<AbstractCommand> parseCommand(String line) {
        String[] args = line.split(" ", 2);
        if (args.length == 0) {
            System.out.println("\tNO COMMANDS GIVEN");
            return Optional.empty();
        }
        String[] cmdArgs;
        try {
            Commands command = Commands.valueOf(args[0].toUpperCase());
            switch (command) {
            case LIST:
                return Optional.of(new ListCommand());
            case MARK:
                return Optional.of(new MarkCommand(Integer.parseInt(args[1].strip())));
            case UNMARK:
                return Optional.of(new UnmarkCommand(Integer.parseInt(args[1].strip())));
            case TODO:
                if (args.length != 2 || args[1].isEmpty()) {
                    throw new CommandParseException("COMMAND todo REQUIRES DESCRIPTION ARGUMENT");
                }
                Todo todo = new Todo(args[1].strip());
                String successMessage = String.format("\tADDED TODO: %s\n", todo.toString());
                return Optional.of(new AddCommand(todo, successMessage));
            case DEADLINE:
                cmdArgs = args[1].split("/by");
                if (cmdArgs.length != 2) {
                    throw new CommandParseException("COMMAND deadline REQUIRES ARGUMENT /by");
                }
                Deadline deadline = new Deadline(
                        cmdArgs[0].strip(),
                        LocalDate.parse(cmdArgs[1].strip()));
                successMessage = String.format("\tADDED DEADLINE: %s\n", deadline.toString());
                return Optional.of(new AddCommand(deadline, successMessage));
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
                successMessage = String.format("\tADDED EVENT: %s\n", event.toString());
                return Optional.of(new AddCommand(event, successMessage));
            case DELETE:
                return Optional.of(new DeleteCommand(Integer.parseInt(args[1].strip())));
            default:
                throw new CommandParseException("UNKNOWN COMMAND GIVEN");
            }
        } catch (NumberFormatException err) {
            System.out.printf("\tERROR: INVALID NUMBER GIVEN FOR COMMAND: %s\n", args[0]);
        } catch (CommandParseException err) {
            System.out.printf("\t%s\n", err.getMessage());
        } catch (IllegalArgumentException err) {
            System.out.printf("\tERROR: INVALID COMMAND GIVEN %s\n", args[0]);
        } catch (DateTimeParseException err) {
            System.out.println("\tERROR: COULD NOT PARSE DATE ARGUMENT");
        }
        return Optional.empty();
    }
}
