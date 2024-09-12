package orion.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import orion.orionexceptions.FileInitializationException;
import orion.orionexceptions.InvalidDateFormatException;
import orion.orionexceptions.InvalidDeadlineException;
import orion.orionexceptions.InvalidDeleteException;
import orion.orionexceptions.InvalidEventException;
import orion.orionexceptions.InvalidIndexException;
import orion.orionexceptions.InvalidListException;
import orion.orionexceptions.InvalidMarkException;
import orion.orionexceptions.InvalidTodoException;
import orion.storage.Storage;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.tasklist.TaskList;

/**
 * Parses and validates user commands.
 *
 * <p>
 * This class is responsible for validating the format of user commands and
 * extracting relevant information from the commands.
 * </p>
 */
public class Parser {

    /**
     * Validates that the command is a valid list command.
     *
     * @param parts the parts of the command to be validated
     * @return true if the command is valid, false otherwise
     * @throws InvalidListException if the command is invalid
     */
    public boolean validateListCommand(String... parts) throws InvalidListException {
        if (parts == null || parts.length > 1 || !parts[0].equals("list")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        }
        return true;
    }

    /**
     * Validates that the command starts with one of the valid commands and has the
     * correct format.
     *
     * @param parts         The command parts.
     * @param validCommands The list of valid commands (e.g., "mark", "unmark",
     *                      "delete").
     * @throws InvalidMarkException if the command format is invalid.
     */
    private void validateCommandParts(String[] parts, String... validCommands) throws InvalidMarkException {
        if (parts == null || parts.length < 2 || !Arrays.asList(validCommands).contains(parts[0])) {
            throw new InvalidMarkException("Invalid command: " + (parts == null ? "null" : String.join(" ", parts)));
        }
    }

    /**
     * Checks if the given string can be parsed as an integer.
     *
     * @param str the string to check
     * @return true if the string can be parsed as an integer, false otherwise
     */
    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Extracts the task index from the given command parts.
     *
     * @param parts the command parts
     * @return the task index
     * @throws InvalidMarkException if the command format is invalid
     */
    private int extractTaskIndex(String[] parts) throws InvalidMarkException {
        // Join the parts starting from the second element onwards (i.e., excluding the
        // command)
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        // Split the joined string into individual parts, expecting a single index
        String[] splitParts = joinedString.split(" ");

        // Validate that there is exactly one part and it's a valid integer
        if (splitParts.length != 1 || !isInteger(splitParts[0])) {
            throw new InvalidMarkException("Invalid index: " + joinedString);
        }

        // Parse and return the index as an integer
        return Integer.parseInt(splitParts[0]);
    }

    /**
     * Validates that the given index is valid in the task list.
     *
     * <p>
     * This method creates a new TaskList instance and checks if the given index is
     * valid. If the index is invalid, it throws an InvalidIndexException.
     * </p>
     *
     * @param index the index to validate
     * @throws InvalidIndexException       if the index is invalid
     * @throws FileInitializationException if there is an issue with file reading
     */
    private void validateTaskIndex(int index) throws InvalidIndexException, FileInitializationException {
        TaskList manager = new TaskList(new Storage());

        if (!manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }
    }

    /**
     * Validates that the given command parts form a valid mark or unmark command.
     *
     * <p>
     * A valid mark or unmark command consists of the command name followed by a
     * single
     * integer index. The index is validated to ensure it is a valid task index.
     * </p>
     *
     * @param parts the command parts
     * @return the task index
     * @throws InvalidMarkException        if the command format is invalid
     * @throws InvalidIndexException       if the index is invalid
     * @throws FileInitializationException if there is an issue with file reading
     */
    public int validateMarkAndUnMarkCommand(String... parts)
            throws InvalidMarkException, InvalidIndexException, FileInitializationException {

        this.validateCommandParts(parts, "mark", "unmark");

        if (parts == null || parts.length < 2 || !(parts[0].equals("mark") || parts[0].equals("unmark"))) {
            throw new InvalidMarkException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || !isInteger(split_parts[0])) {
            throw new InvalidMarkException(joinedString);
        }
        int index = extractTaskIndex(parts);
        return index;
    }

    public String validateTodoCommand(String... parts) throws InvalidTodoException {
        if (parts == null || parts.length < 2 || !parts[0].equals("todo")) {
            throw new InvalidTodoException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1];
    }

    /**
     * Validates that the given command parts form a valid event command.
     *
     * <p>
     * A valid event command consists of the command name followed by a
     * description, a start date and time, and an end date and time. The dates
     * and times are validated to ensure they are in the correct format and
     * correctly represent a valid date and time.
     * </p>
     *
     * @param parts the command parts
     * @return EventDetails object containing the details of the event
     * @throws InvalidEventException      if the command format is invalid
     * @throws InvalidDateFormatException if the date and time format is invalid
     */
    public EventDetails validateEventCommand(String... parts) throws InvalidEventException, InvalidDateFormatException {
        if (parts == null || parts.length < 2 || !parts[0].equals("event")) {
            throw new InvalidEventException(parts == null ? "null" : String.join(" ", parts));
        }

        String[] eventDetails = parts[1].split("/from|/to");
        if (eventDetails.length != 3) {
            throw new InvalidEventException(String.join(" ", parts));
        }

        String description = eventDetails[0].trim();
        String fromStr = eventDetails[1].trim();
        String toStr = eventDetails[2].trim();

        if (description.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new InvalidEventException(String.join(" ", parts));
        }

        LocalDateTime from = parseDateTime(fromStr);
        LocalDateTime to = parseDateTime(toStr);

        return new EventDetails(description, from, to);
    }

    /**
     * Validates that the given command parts form a valid deadline command.
     *
     * <p>
     * A valid deadline command consists of the command name followed by a
     * description and a due date specified by '/by'. The description and due
     * date are validated to ensure they are not empty.
     * </p>
     *
     * @param parts the command parts
     * @return DeadlineDetails object containing the details of the deadline
     * @throws InvalidDeadlineException   if the command format is invalid
     * @throws InvalidDateFormatException if the date and time format is invalid
     */
    public DeadlineDetails validateDeadlineCommand(String... parts)
            throws InvalidDeadlineException, InvalidDateFormatException {
        if (parts == null || parts.length < 2 || !parts[0].equals("deadline")) {
            throw new InvalidDeadlineException(
                    "Invalid deadline command format. Use: deadline <description> /by <due date>");
        }

        String fullCommand = String.join(" ", parts);
        int byIndex = fullCommand.indexOf("/by");

        if (byIndex == -1) {
            throw new InvalidDeadlineException("Deadline must include a due date specified by '/by'");
        }

        String description = fullCommand.substring("deadline".length(), byIndex).trim();
        String byStr = fullCommand.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty() || byStr.isEmpty()) {
            throw new InvalidDeadlineException("Description and due date cannot be empty");
        }

        LocalDateTime by = parseDateTime(byStr);

        return new DeadlineDetails(description, by);
    }

    /**
     * Validates that the given command parts form a valid delete command.
     *
     * <p>
     * A valid delete command consists of the command name followed by a
     * single number representing the task list index of the task to delete.
     * The index is validated to ensure it is a valid index in the task list.
     * </p>
     *
     * @param parts the command parts
     * @return the task list index of the task to delete
     * @throws InvalidDeleteException      if the command format is invalid
     * @throws InvalidIndexException       if the index is invalid
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public int validateDeleteCommand(String... parts)
            throws InvalidDeleteException, InvalidIndexException, FileInitializationException {
        if (parts == null || parts.length < 2 || !parts[0].equals("delete")) {
            throw new InvalidDeleteException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || !isInteger(split_parts[0])) {
            throw new InvalidDeleteException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        TaskList manager = new TaskList(new Storage());
        if (!manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }

        return index;
    }

    /**
     * Parses a string representation of a date and time into a
     * <code>LocalDateTime</code> object.
     *
     * <p>
     * The string representation should be in the format
     * <code>dd/MM/yyyy HHmm</code> or <code>dd/MM/yyyy</code>. If the
     * string representation is invalid, an <code>InvalidDateFormatException</code>
     * is thrown.
     * </p>
     *
     * @param dateTimeStr the string representation of the date and time
     * @return a <code>LocalDateTime</code> object representing the parsed date
     *         and time
     * @throws InvalidDateFormatException if the date and time format is invalid
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws InvalidDateFormatException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(dateTimeStr, dateFormatter);
                return LocalDateTime.of(date, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ex) {
                throw new InvalidDateFormatException(dateTimeStr);
            }
        }
    }

    /**
     * Validates that the given command parts form a valid find command.
     *
     * <p>
     * A valid find command consists of the command name followed by a
     * keyword.
     * </p>
     *
     * @param parts the command parts
     * @return the keyword
     * @throws InvalidListException if the command format is invalid
     */
    public String validateFindCommand(String... parts) throws InvalidListException {
        if (parts == null || parts.length < 2 || !parts[0].equals("find")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1].trim();
    }

    /**
     * Validates that the given command parts form a valid mark or unmark command.
     *
     * <p>
     * A valid mark or unmark command consists of the command name followed by a
     * single integer index. The index is validated to ensure it is a valid task
     * index.
     * </p>
     *
     * @param manager the task list manager
     * @param parts   the command parts
     * @return the task index
     * @throws InvalidMarkException        if the command format is invalid
     * @throws InvalidIndexException       if the index is invalid
     * @throws FileInitializationException if there is an issue with file reading
     */
    public int validateMarkAndUnMarkCommand(TaskList manager, String... parts)
            throws InvalidMarkException, InvalidIndexException, FileInitializationException {
        // Validate that the command is either "mark" or "unmark"
        validateCommandParts(parts, "mark", "unmark");

        // Extract the task index from the command
        int index = extractTaskIndex(parts);

        // Validate the task index using the task manager
        validateTaskIndex(index);

        return index;
    }

    /**
     * Validates that the given command parts form a valid delete command.
     *
     * <p>
     * A valid delete command consists of the command name followed by a
     * single integer index. The index is validated to ensure it is a valid
     * task index.
     * </p>
     *
     * @param manager the task list manager
     * @param parts   the command parts
     * @return the task index
     * @throws InvalidDeleteException      if the command format is invalid
     * @throws InvalidIndexException       if the index is invalid
     * @throws FileInitializationException if there is an issue with file reading
     */
    public int validateDeleteCommand(TaskList manager, String... parts)
            throws InvalidDeleteException, InvalidIndexException, FileInitializationException {
        if (parts == null || parts.length < 2 || !parts[0].equals("delete")) {
            throw new InvalidDeleteException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || !isInteger(split_parts[0])) {
            throw new InvalidDeleteException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        if (!manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }

        return index;
    }

}
