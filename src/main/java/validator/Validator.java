package validator;
import orionExceptions.*;
import taskManager.TaskManager;

import java.util.Arrays;

public class Validator {

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


    public int validateMarkAndUnMarkCommand(String[] parts, TaskManager manager) throws InvalidMarkException, InvalidIndexException {
        if (parts == null || parts.length < 2 || !parts[0].equals("mark")) {
            throw new InvalidMarkException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || ! isInteger(split_parts[0])) {
            throw new InvalidMarkException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        if (! manager.isvalidIndex(index)) {
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

    public String[] validateEventCommand(String[] parts) throws InvalidEventException {
        if (parts == null || parts.length < 2 || !parts[0].equals("event")) {
            throw new InvalidEventException(parts == null ? "null" : String.join(" ", parts));
        }

        String[] eventDetails = parts[1].split("/from|/to");
        if (eventDetails.length != 3) {
            throw new InvalidEventException(String.join(" ", parts));
        }

        String description = eventDetails[0].trim();
        String from = eventDetails[1].trim();
        String to = eventDetails[2].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException(String.join(" ", parts));
        }

        return new String[]{description, from, to};
    }

    public String[] validateDeadlineCommand(String[] parts) throws InvalidDeadlineException {
        if (parts == null || parts.length < 2 || !parts[0].equals("deadline")) {
            throw new InvalidDeadlineException("Invalid deadline command format. Use: deadline <description> /by <due date>");
        }

        String fullCommand = String.join(" ", parts);
        int byIndex = fullCommand.indexOf("/by");

        if (byIndex == -1) {
            throw new InvalidDeadlineException("Deadline must include a due date specified by '/by'");
        }

        String description = fullCommand.substring("deadline".length(), byIndex).trim();
        String by = fullCommand.substring(byIndex + "/by".length()).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new InvalidDeadlineException("Description and due date cannot be empty");
        }

        return new String[]{description, by};
    }

    public int validateDeleteCommand(String[] parts, TaskManager manager) throws InvalidDeleteException, InvalidIndexException {
        if (parts == null || parts.length < 2 || !parts[0].equals("delete")) {
            throw new InvalidDeleteException(parts == null ? "null" : String.join(" ", parts));
        }
        String joinedString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

        String[] split_parts = joinedString.split(" ");
        if (split_parts.length != 1 || !isInteger(split_parts[0])) {
            throw new InvalidDeleteException(joinedString);
        }
        int index = Integer.parseInt(split_parts[0]);

        if (!manager.isvalidIndex(index)) {
            throw new InvalidIndexException(index, manager.getSize());
        }

        return index;
    }



}
