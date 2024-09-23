package rizz.command;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import rizz.source.TaskList;

/**
 * The Parser class is responsible for interpreting user input and converting it into specific commands.
 * It parses the input string and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     * If the input matches a known command, it creates a new Command object; otherwise, it returns null.
     *
     * Supported commands:
     * - "bye": Exit the application.
     * - "list": List all tasks.
     * - "tod0 (details)": Add a new ToDo task.
     * - "event (details) /from (start time) /to (end time)": Add a new Event task.
     * - "deadline (details) /by (due date)": Add a new Deadline task.
     * - "mark (index)": Mark a task as done.
     * - "unmark (index)": Unmark a task.
     * - "delete (index)": Delete a task.
     * - "find (details)": Find a task.
     *
     * @param input The user input string to be parsed.
     * @param taskList The current TaskList to validate task indices for commands like mark, unmark, and delete.
     * @return The Command object representing the parsed command, or null if the input is invalid.
     */
    public static Command parseCommand(String input, TaskList taskList) {
        String[] splitInput = input.split(" ", 2);
        String commandString= splitInput[0].toUpperCase(); // convert to enum
        String details = splitInput.length > 1 ? splitInput[1] : null;

        CommandType commandType;

        try {
            // convert input -> CommandType enum val
            commandType = CommandType.valueOf(commandString);
        } catch (IllegalArgumentException e) {
            return null;
        }
        assert commandType != null: "Command type null at dis point";

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return new AddToDoCommand(details);
        case EVENT:
            return parseEventCommand(details);
        case DEADLINE:
            return parseDeadlineCommand(details);
        case FIND:
            return new FindCommand(details);
        case UNDO:
            return new UndoCommand();
        case MARK, UNMARK, DELETE:
            return processMultipleTasks(details, commandType, taskList);
        default:
            return null;
        }
    }

    /**
     * Parses and creates an Event command from the user input.
     *
     * Expected format: "event details /from yyyy-MM-dd HHmm /to HHmm"
     *
     * @param details The event details, including description, start time, and end time.
     * @return A new AddEventCommand with the parsed details.
     */
    private static Command parseEventCommand(String details) {
        String[] eventParts = details.split("/from|/to");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        LocalDateTime eventStart = LocalDateTime.parse(eventParts[1].trim(), dateTimeFormatter);
        LocalTime eventEnd = LocalTime.parse(eventParts[2].trim(), timeFormatter);
        return new AddEventCommand(eventParts[0].trim(), eventStart, eventEnd);
    }

    /**
     * Parses and creates a Deadline command from the user input.
     *
     * Expected format: "deadline details /by yyyy-MM-dd HHmm"
     *
     * @param details The deadline details, including description and due date.
     * @return A new AddDeadlineCommand with the parsed details.
     */
    private static Command parseDeadlineCommand(String details) {
        String[] deadlineParts = details.split("/by");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        LocalDateTime by = LocalDateTime.parse(deadlineParts[1].trim(), formatter);
        return new AddDeadlineCommand(deadlineParts[0].trim(), by);
    }

    /**
     * Processes commands that operate on multiple tasks, such as mark, unmark, or delete.
     * Validates task indices to ensure they are within the valid range.
     *
     * @param details The details containing the task indices (space-separated).
     * @param commandType The type of the command (MARK, UNMARK, DELETE).
     * @param taskList The current TaskList to validate the task indices against.
     * @return The Command object representing the operation (e.g., MarkCommand, UnmarkCommand, DeleteCommand).
     * @throws IllegalArgumentException If any of the task indices are out of bounds.
     */
    private static Command processMultipleTasks(String details, CommandType commandType, TaskList taskList) {
        // Split since they are space-separated
        String[] indices = details.split(" ");

        int[] index = new int[indices.length];
        for (int i = 0; i < indices.length; i++) {
            index[i] = Integer.parseInt(indices[i].trim());
            if (index[i] < 1 || index[i] > taskList.getLength()) {
                throw new IllegalArgumentException("Task index " + index[i] + " is out of bounds.");
            }
        }
        switch (commandType) {
        case MARK:
            return new MarkCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        case DELETE:
            return new DeleteCommand(index);
        default:
            return null;
        }
    }
}
