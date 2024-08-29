package bob.parser;

import bob.data.BobException;
import bob.commands.Bye;
import bob.commands.Command;
import bob.commands.Delete;
import bob.commands.Event;
import bob.commands.List;
import bob.commands.Mark;
import bob.commands.On;
import bob.commands.Unmark;
import bob.commands.Find;
import bob.commands.Todo;
import bob.commands.Deadline;
import bob.tasks.DeadlineTask;
import bob.tasks.EventTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public enum CommandType {
        LIST, UNMARK, MARK, ON, DELETE, FIND, TODO, DEADLINE, EVENT, INVALID, BYE
    }

    public static Command parse(String fullCommand) throws BobException {
        String[] commandParts = fullCommand.split(" "); // Split into command and arguments
        String commandWord = commandParts[0].toUpperCase();

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandWord); // Convert string to enum
        } catch (IllegalArgumentException e) {
            throw new BobException("Invalid command. Please enter a valid command. Valid commands are: list, unmark, mark, delete, on, todo, deadline, event, and bye.");
        }

        switch (commandType) {
            case LIST:
                return prepareList();
            case MARK:
                return prepareMark(fullCommand);
            case UNMARK:
                return prepareUnmark(fullCommand);
            case ON:
                return prepareOn(fullCommand);
            case DELETE:
                return prepareDelete(fullCommand);
            case FIND:
                return prepareFine(fullCommand);
            case TODO:
                return prepareTodo(fullCommand);
            case DEADLINE:
                return prepareDeadline(fullCommand);
            case EVENT:
                return prepareEvent(fullCommand);
            case BYE:
                return new Bye();
            case INVALID:
                throw new BobException("Invalid command. Please enter a valid command. Valid commands are: list, unmark, mark, delete, on, todo, deadline, event, and bye.");
        }
        return null;
    }

    private static List prepareList() {
        return new List();
    }

    private static Mark prepareMark(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BobException("PLease provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new BobException("Please enter a valid task number.");
        }
        return new Mark(index);
    }

    private static Unmark prepareUnmark(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BobException("PLease provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new BobException("Please enter a valid task number.");
        }
        return new Unmark(index);
    }

    private static On prepareOn(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BobException("Please provide a date in the format: dd/MM/yyyy.");
        }

        String date = parts[1];
        LocalDate parsedDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date. Please enter a valid date in the format: dd/MM/yyyy.");
        }
        return new On(parsedDate);
    }

    private static Delete prepareDelete(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new BobException("PLease provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new BobException("Please enter a valid task number.");
        }
        return new Delete(index);
    }

    private static Find prepareFine(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ",2);
        if (parts.length < 2) {
            throw new BobException("Please provide a description.");
        }
        return new Find(parts[1]);
    }

    private static Todo prepareTodo(String fullCommand) throws BobException {
        String description = fullCommand.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new BobException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    private static Deadline prepareDeadline(String fullCommand) throws BobException {
        String[] parts = fullCommand.split("/by");
        if (parts.length < 2) {
            throw new BobException("The date of a deadline cannot be empty. Please enter in the format: description /by <date>");
        }
        String description = parts[0].substring("deadline".length()).trim();
        String date = parts[1].trim();
        if (description.isEmpty()) {
            throw new BobException("The description of a deadline cannot be empty.");
        }
        try {
            LocalDateTime by = DeadlineTask.parseDateTime(date);
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date. Please enter in the format: yyyy-MM-dd HH:mm");
        }
    }

    private static Event prepareEvent(String fullCommand) throws BobException {
        String[] parts = fullCommand.split("/from|/to");
        if (parts.length < 3) {
            throw new BobException("The start and end date/time of an event cannot be empty. Please enter in the format: description /from <start> /to <end>");
        }
        String description = parts[0].substring("event".length()).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (description.isEmpty()) {
            throw new BobException("The description of an event cannot be empty.");
        }
        try {
            LocalDateTime fromTime = EventTask.parseDateTime(from);
            return new Event(description, fromTime, to);
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid start and end date. Please enter in the format: /from yyyy-MM-dd HH:mm /to: yyyy-MM-dd HH:mm or HH:mm");
        }
    }
}
