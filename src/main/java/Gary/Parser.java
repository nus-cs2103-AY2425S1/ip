package Gary;

import Gary.command.AddCommand;
import Gary.command.ByeCommand;
import Gary.command.Command;
import Gary.command.DeleteCommand;
import Gary.command.EditTaskCommand;
import Gary.command.FindCommand;
import Gary.command.ShowListCommand;
import Gary.task.Deadline;
import Gary.task.Event;
import Gary.task.ToDo;

/**
 * The {@code Parser} class is responsible for interpreting user input commands and
 * converting them into specific {@code Command} objects that can be executed.
 */
public class Parser {

    // Error messages
    private static final String TODO_FORMAT = "Please provide your ToDo task in the following format:\n"
            + "todo <task name>\n";
    private static final String DEADLINE_FORMAT = "Please provide your Deadline task in the following format:\n"
            + "deadline <task name> /by <yyyy-mm-dd>\n";
    private static final String EVENT_FORMAT = "Please provide your Event task in the following format:\n"
            + "event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n";
    private static final String FIND_FORMAT = "Please provide a keyword to search for tasks:\n"
            + "find <keyword>\n";
    private static final String UNKNOWN_COMMAND = "Sorry! I do not understand what is this!!";

    /**
     * Parses a full user command and returns the corresponding {@code Command} object.
     *
     * @param fullCommand The full string input from the user.
     * @return A {@code Command} object representing the user input.
     * @throws GaryException If the command is not recognized or has an invalid format.
     */
    static Command parse(String fullCommand) throws GaryException {
        // Assertion: Ensure that fullCommand is not null and not empty
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Input command cannot be null or empty";

        String[] split = fullCommand.trim().split(" ", 2);
        String taskType = split[0].toLowerCase();

<<<<<<< HEAD
        switch (taskType) {

        case "todo":
            return parseToDoCommand(split);
        case "deadline":
<<<<<<< HEAD
            return parseDeadlineCommand(split);
        case "event":
            return parseEventCommand(split);
        case "mark":
            return parseEditTaskCommand(true, split);
        case "unmark":
            return parseEditTaskCommand(false, split);
        case "delete":
            return parseDeleteCommand(split);
        case "list":
            return new ShowListCommand();
        case "find":
            return parseFindCommand(split);
        case "bye":
            return new ByeCommand();
        default:
            throw new GaryException(UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses a ToDo command and returns the corresponding {@code AddCommand} object.
     *
     * @param split The split command array containing the task description.
     * @return An {@code AddCommand} object with the ToDo task.
     * @throws GaryException If the command format is invalid.
     */
    private static Command parseToDoCommand(String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException(TODO_FORMAT);
        }
        return new AddCommand(new ToDo(split[1].trim()));
    }

    /**
     * Parses a Deadline command and returns the corresponding {@code AddCommand} object.
     *
     * @param split The split command array containing the task description and due date.
     * @return An {@code AddCommand} object with the Deadline task.
     * @throws GaryException If the command format is invalid.
     */
    private static Command parseDeadlineCommand(String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException(DEADLINE_FORMAT);
        }
        String[] split1 = split[1].split("/by");
        if (split1.length != 2 || split1[0].trim().isEmpty() || split1[1].trim().isEmpty()) {
            throw new GaryException(DEADLINE_FORMAT);
        }
        String description = split1[0].trim();
        String dueDate = split1[1].trim();
        return new AddCommand(new Deadline(description, dueDate));
    }

    /**
     * Parses an Event command and returns the corresponding {@code AddCommand} object.
     *
     * @param split The split command array containing the task description, start, and end times.
     * @return An {@code AddCommand} object with the Event task.
     * @throws GaryException If the command format is invalid.
     */
    private static Command parseEventCommand(String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException(EVENT_FORMAT);
        }
        String[] firstSplit = split[1].trim().split("/from");
        if (firstSplit.length != 2) {
            throw new GaryException(EVENT_FORMAT);
        }
        String[] secondSplit = firstSplit[1].trim().split("/to");
        if (secondSplit.length != 2 || firstSplit[0].trim().isEmpty() || secondSplit[0].trim().isEmpty() || secondSplit[1].trim().isEmpty()) {
            throw new GaryException(EVENT_FORMAT);
        }
        String eventName = firstSplit[0].trim();
        String start = secondSplit[0].trim();
        String end = secondSplit[1].trim();
        return new AddCommand(new Event(eventName, start, end));
    }

    /**
     * Parses a mark or unmark command and returns the corresponding {@code EditTaskCommand} object.
     *
     * @param isMark A boolean indicating whether to mark (true) or unmark (false) the task.
     * @param split The split command array containing the task index.
     * @return An {@code EditTaskCommand} object to mark or unmark the task.
     * @throws GaryException If the command format is invalid or the task index is not an integer.
     */
    private static Command parseEditTaskCommand(boolean isMark, String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException("Please specify the task number to mark/unmark.");
        }
        try {
            int taskIndex = Integer.parseInt(split[1].trim()) - 1;
            return new EditTaskCommand(isMark, taskIndex);
        } catch (NumberFormatException e) {
            throw new GaryException("Invalid task number. Please enter a valid integer.");
        }
    }

    /**
     * Parses a delete command and returns the corresponding {@code DeleteCommand} object.
     *
     * @param split The split command array containing the task index to delete.
     * @return A {@code DeleteCommand} object to remove the specified task.
     * @throws GaryException If the command format is invalid or the task index is not an integer.
     */
    private static Command parseDeleteCommand(String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException("Please specify the task number to delete.");
        }
        try {
            int taskIndex = Integer.parseInt(split[1].trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new GaryException("Invalid task number. Please enter a valid integer.");
        }
    }

    /**
     * Parses a find command and returns the corresponding {@code FindCommand} object.
     *
     * @param split The split command array containing the keyword to search for.
     * @return A {@code FindCommand} object to search for tasks with the specified keyword.
     * @throws GaryException If the command format is invalid.
     */
    private static Command parseFindCommand(String[] split) throws GaryException {
        if (split.length != 2) {
            throw new GaryException(FIND_FORMAT);
        }
        return new FindCommand(split[1].trim());
    }
}
