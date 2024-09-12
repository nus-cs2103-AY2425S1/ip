package orion.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import orion.orionExceptions.FileInitializationException;
import orion.orionExceptions.InvalidDateFormatException;
import orion.orionExceptions.InvalidDeadlineException;
import orion.orionExceptions.InvalidDeleteException;
import orion.orionExceptions.InvalidEventException;
import orion.orionExceptions.InvalidIndexException;
import orion.orionExceptions.InvalidListException;
import orion.orionExceptions.InvalidMarkException;
import orion.orionExceptions.InvalidTodoException;
import orion.storage.Storage;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.tasklist.TaskList;

public class Parser {

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

    private void validateTaskIndex(int index) throws InvalidIndexException, FileInitializationException {
        TaskList manager = new TaskList(new Storage());

        if (!manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }
    }

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

    public String validateFindCommand(String... parts) throws InvalidListException {
        if (parts == null || parts.length < 2 || !parts[0].equals("find")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1].trim();
    }

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
