package parser;
import orionExceptions.*;
import task.DeadlineDetails;
import task.EventDetails;
import taskList.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public boolean validateListCommand(String[] parts) throws InvalidListException{
        if (parts == null || parts.length > 1 || !parts[0].equals("list")) {
            throw new InvalidListException(parts == null ? "null" : String.join(" ", parts));
        } else {
            return true;
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


    public int validateMarkAndUnMarkCommand(String[] parts, TaskList manager) throws InvalidMarkException, InvalidIndexException, FileInitializationException {
        if (parts == null || parts.length < 2 || !(parts[0].equals("mark") || parts[0].equals("unmark"))) {
            throw new InvalidMarkException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || ! isInteger(split_parts[0])) {
            System.out.println(Arrays.toString(split_parts));
            throw new InvalidMarkException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        if (! manager.isValidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }

        return index;

    }

    public String validateTodoCommand(String[] parts) throws InvalidTodoException {
        if (parts == null || parts.length < 2 || !parts[0].equals("todo")) {
            throw new InvalidTodoException(parts == null ? "null" : String.join(" ", parts));
        }

        return parts[1];
    }

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

    public DeadlineDetails validateDeadlineCommand(String[] parts) throws InvalidDeadlineException, InvalidDateFormatException {
        if (parts == null || parts.length < 2 || !parts[0].equals("deadline")) {
            throw new InvalidDeadlineException("Invalid deadline command format. Use: deadline <description> /by <due date>");
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

    public int validateDeleteCommand(String[] parts, TaskList manager) throws InvalidDeleteException, InvalidIndexException, FileInitializationException {
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

    private LocalDateTime parseDateTime(String dateTimeStr) throws InvalidDateFormatException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        try {
            // Try parsing with time
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                // If time is not provided, parse date only and default to 00:00
                LocalDate date = LocalDate.parse(dateTimeStr, dateFormatter);
                return LocalDateTime.of(date, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ex) {
                throw new InvalidDateFormatException(dateTimeStr);
            }
        }
    }




    }
