package orion.parser;

import orion.orionExceptions.FileInitializationException;
import orion.orionExceptions.InvalidDateFormatException;
import orion.orionExceptions.InvalidDeadlineException;
import orion.orionExceptions.InvalidDeleteException;
import orion.orionExceptions.InvalidEventException;
import orion.orionExceptions.InvalidIndexException;
import orion.orionExceptions.InvalidListException;
import orion.orionExceptions.InvalidMarkException;
import orion.orionExceptions.InvalidTodoException;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.taskList.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Provides methods to parse and validate user commands for managing tasks.
 *
 * <p>
 * This class handles parsing and validation of commands for different types of
 * tasks,
 * including todos, deadlines, events, marking, unmarking, and deleting tasks.
 * </p>
 *
 * @author Pradyumna
 */
public class Parser {

    /**
     * Validates the list command.
     *
     * @param parts the command split into parts.
     * @return true if the command is valid.
     * @throws InvalidListException if the command format is incorrect.
     */
    public boolean validateListCommand(String[] parts) throws InvalidListException {
        if (parts == null || parts.length > 1 || !parts[0].equals("list")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        }
        return true;
    }

    /**
     * Checks if a string can be parsed as an integer.
     *
     * @param str the string to check.
     * @return true if the string is an integer, false otherwise.
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
     * Validates the mark or unmark command.
     *
     * @param parts   the command split into parts.
     * @param manager the task list manager.
     * @return the index of the task.
     * @throws InvalidMarkException        if the command format is incorrect.
     * @throws InvalidIndexException       if the task index is invalid.
     * @throws FileInitializationException if there is an issue with file
     *                                     initialization.
     */
    public int validateMarkAndUnMarkCommand(String[] parts, TaskList manager)
            throws InvalidMarkException, InvalidIndexException, FileInitializationException {
        if (parts == null || parts.length < 2 || !(parts[0].equals("mark") || parts[0].equals("unmark"))) {
            throw new InvalidMarkException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || !isInteger(split_parts[0])) {
            System.out.println(Arrays.toString(split_parts));
            throw new InvalidMarkException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        if (!manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }

        return index;
    }

    /**
     * Validates the todo command.
     *
     * @param parts the command split into parts.
     * @return the task description.
     * @throws InvalidTodoException if the command format is incorrect.
     */
    public String validateTodoCommand(String[] parts) throws InvalidTodoException {
        if (parts == null || parts.length < 2 || !parts[0].equals("todo")) {
            throw new InvalidTodoException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1];
    }

    /**
     * Validates the event command.
     *
     * @param parts the command split into parts.
     * @return the event details.
     * @throws InvalidEventException      if the command format is incorrect.
     * @throws InvalidDateFormatException if the date format is invalid.
     */
    public EventDetails validateEventCommand(String[] parts) throws InvalidEventException, InvalidDateFormatException {
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
     * Validates the deadline command.
     *
     * @param parts the command split into parts.
     * @return the deadline details.
     * @throws InvalidDeadlineException   if the command format is incorrect.
     * @throws InvalidDateFormatException if the date format is invalid.
     */
    public DeadlineDetails validateDeadlineCommand(String[] parts)
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
     * Validates the delete command.
     *
     * @param parts   the command split into parts.
     * @param manager the task list manager.
     * @return the index of the task to delete.
     * @throws InvalidDeleteException      if the command format is incorrect.
     * @throws InvalidIndexException       if the task index is invalid.
     * @throws FileInitializationException if there is an issue with file
     *                                     initialization.
     */
    public int validateDeleteCommand(String[] parts, TaskList manager)
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

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr the date and time string to parse.
     * @return the LocalDateTime object.
     * @throws InvalidDateFormatException if the date format is invalid.
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

    public String validateFindCommand(String[] parts) throws InvalidListException {
        if (parts == null || parts.length < 2 || !parts[0].equals("find")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1].trim();
    }

}
