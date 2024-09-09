package strand;

import strand.command.AddCommand;
import strand.command.ByeCommand;
import strand.command.Command;
import strand.command.DeleteCommand;
import strand.command.FindCommand;
import strand.command.ListCommand;
import strand.command.MarkCommand;
import strand.exception.StrandDescNotFoundException;
import strand.exception.StrandException;
import strand.exception.StrandNumberNotFoundException;
import strand.exception.StrandWrongCommandException;

/**
 * The {@code Parser} class interprets user input and converts it into
 * corresponding commands within the Strand application.
 * <p>
 * This class may throw exceptions if the input is invalid or unrecognized.
 * </p>
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input as a string.
     * @return The command based on the user input.
     * @throws StrandException If the input is invalid or the command is not recognized.
     */
    static Command parse(String input) throws StrandException {
        assert input != null : "Input cannot be null";
        String[] split = input.trim().split("\\s+", 2);
        if (split.length == 0) {
            throw new StrandWrongCommandException();
        }
        CommandEnums command = getCommand(split[0].toUpperCase());

        return switch (command) {
        case DELETE, MARK, UNMARK -> parseIndex(command, split);
        case BYE -> new ByeCommand();
        case LIST -> new ListCommand();
        case TODO, DEADLINE, EVENT, FIND -> parseDescription(command, split);
        default -> throw new StrandWrongCommandException();
        };
    }

    /**
     * Retrieves the command enum from the input string.
     *
     * @param input The input string representing the command.
     * @return The corresponding command enum.
     * @throws StrandWrongCommandException If the command is not recognized.
     */
    private static CommandEnums getCommand(String input) throws StrandWrongCommandException {
        assert input != null : "Command input cannot be null";
        try {
            return CommandEnums.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new StrandWrongCommandException();
        }
    }

    /**
     * Parses commands that require an index (DELETE, MARK, UNMARK).
     *
     * @param command The command enum.
     * @param split   The split input string.
     * @return The command object based on the index.
     * @throws StrandNumberNotFoundException If the index is missing or invalid.
     */
    private static Command parseIndex(
            CommandEnums command, String[] split) throws StrandException {
        assert command != null : "Command cannot be null";
        if (split.length < 2) {
            throw new StrandNumberNotFoundException(split[0]);
        }
        try {
            int index = Integer.parseInt(split[1]);
            return switch (command) {
            case DELETE -> new DeleteCommand(index);
            case MARK -> new MarkCommand(index, true);
            case UNMARK -> new MarkCommand(index, false);
            default -> throw new StrandWrongCommandException();
            };
        } catch (NumberFormatException e) {
            throw new StrandNumberNotFoundException(split[0]);
        }
    }

    /**
     * Parses task-related commands (TODO, DEADLINE, EVENT).
     *
     * @param command The command enum.
     * @param split   The split input string.
     * @return The command object based on the task details.
     * @throws StrandDescNotFoundException If the description or other task details are missing.
     */
    private static Command parseDescription(CommandEnums command, String[] split) throws StrandException {
        assert command != null : "Command cannot be null";
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new StrandDescNotFoundException("Description");
        }
        String desc = split[1].trim();
        return switch (command) {
        case TODO -> new AddCommand(desc);
        case DEADLINE -> parseDeadlineCommand(desc);
        case EVENT -> parseEventCommand(desc);
        case FIND -> new FindCommand(desc);
        default -> throw new StrandDescNotFoundException("Description");
        };
    }

    /**
     * Parses a deadline command, extracting the description and deadline details.
     *
     * @param desc The description string.
     * @return The AddCommand object with the deadline details.
     * @throws StrandDescNotFoundException If the deadline details are missing or invalid.
     */
    private static Command parseDeadlineCommand(String desc) throws StrandException {
        assert desc != null : "Description for deadline cannot be null";
        if (!desc.contains(" /by ")) {
            throw new StrandDescNotFoundException("Deadline");
        }
        String description = desc.substring(0, desc.indexOf(" /by ")).trim();
        String deadline = desc.substring(desc.indexOf(" /by ") + 5).trim();
        return new AddCommand(description, deadline);
    }

    /**
     * Parses an event command, extracting the description, start, and end times.
     *
     * @param desc The description string.
     * @return The AddCommand object with the event details.
     * @throws StrandDescNotFoundException If the start or end time details are missing or invalid.
     */
    private static Command parseEventCommand(String desc) throws StrandException {
        assert desc != null : "Description for event cannot be null";
        if (!desc.contains(" /from ") || !desc.contains(" /to ")) {
            throw new StrandDescNotFoundException("Event times");
        }
        String description = desc.substring(0, desc.indexOf(" /from ")).trim();
        String start = desc.substring(desc.indexOf(" /from ") + 7, desc.indexOf(" /to ")).trim();
        String end = desc.substring(desc.indexOf(" /to ") + 5).trim();
        return new AddCommand(description, start, end);
    }

    /**
     * Enumeration of possible command types.
     */
    enum CommandEnums {
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        LIST,
        FIND
    }
}
