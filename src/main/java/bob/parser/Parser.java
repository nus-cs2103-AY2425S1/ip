package bob.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.commands.Bye;
import bob.commands.Command;
import bob.commands.Deadline;
import bob.commands.Delete;
import bob.commands.Event;
import bob.commands.Find;
import bob.commands.List;
import bob.commands.Mark;
import bob.commands.On;
import bob.commands.Remind;
import bob.commands.Todo;
import bob.commands.Unmark;
import bob.data.BobException;
import bob.tasks.DeadlineTask;
import bob.tasks.EventTask;

/**
 * Represents the parser to parse user input into commands.
 */
public class Parser {

    /**
     * Enum for commands.
     */
    public enum CommandType {
        LIST, UNMARK, MARK, ON, DELETE, FIND, TODO, DEADLINE, EVENT, REMIND, BYE
    }

    /**
     * Parses the command from a String and returns a Command.
     *
     * @param fullCommand the full command with command word and arguments
     * @return the parsed command
     * @throws BobException if the user inputs an invalid command
     */
    public static Command parse(String fullCommand) throws BobException {
        String[] commandParts = fullCommand.split(" "); // Split into command and arguments
        String commandWord = commandParts[0].toUpperCase();

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandWord); // Convert string to enum
        } catch (IllegalArgumentException e) {
            throw new BobException("Invalid command. Please enter a valid command. Valid commands are:"
                    + " list, unmark, mark, delete, on, todo, deadline, event, remind, and bye.");
        }

        return switch (commandType) {
            case LIST -> prepareList();
            case MARK -> prepareMark(fullCommand);
            case UNMARK -> prepareUnmark(fullCommand);
            case ON -> prepareOn(fullCommand);
            case DELETE -> prepareDelete(fullCommand);
            case FIND -> prepareFine(fullCommand);
            case TODO -> prepareTodo(fullCommand);
            case DEADLINE -> prepareDeadline(fullCommand);
            case EVENT -> prepareEvent(fullCommand);
            case REMIND -> prepareRemind();
            case BYE -> new Bye();
            default -> throw new BobException("Invalid command. Please enter a valid command. Valid commands are:"
                    + " list, unmark, mark, delete, on, todo, deadline, event, remind and bye.");
        };
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
            throw new BobException("Please provide a task number.");
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
            throw new BobException("Please provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new BobException("Please enter a valid task number.");
        }
        return new Delete(index);
    }

    private static Find prepareFine(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ", 2);
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
            throw new BobException("The date of a deadline cannot be empty. Please enter in the format: "
                    + "description /by <date>");
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
            throw new BobException("The start and end date/time of an event cannot be empty. "
                    + "Please enter in the format: description /from <start> /to <end>");
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
            throw new BobException("Invalid start and end date. Please enter in the format: "
                    + "/from yyyy-MM-dd HH:mm /to: yyyy-MM-dd HH:mm or HH:mm");
        }
    }

    private static Command prepareRemind() {
        return new Remind();
    }
}
