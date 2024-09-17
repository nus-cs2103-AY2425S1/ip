package peppa.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import peppa.commands.Bye;
import peppa.commands.Command;
import peppa.commands.Deadline;
import peppa.commands.Delete;
import peppa.commands.Event;
import peppa.commands.Find;
import peppa.commands.List;
import peppa.commands.Mark;
import peppa.commands.On;
import peppa.commands.Remind;
import peppa.commands.Todo;
import peppa.commands.Unmark;
import peppa.data.PeppaException;
import peppa.tasks.DeadlineTask;
import peppa.tasks.EventTask;

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
     * @throws PeppaException if the user inputs an invalid command
     */
    public static Command parse(String fullCommand) throws PeppaException {
        // Split into command word and arguments to get command type
        String[] commandParts = fullCommand.split(" ");

        String commandWord = commandParts[0].toUpperCase();

        CommandType commandType;

        try {
            // Convert string to enum
            commandType = CommandType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new PeppaException(getInvalidCommandComment());
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
            default -> throw new PeppaException(getInvalidCommandComment());
        };
    }

    private static String getInvalidCommandComment() {
        return "Invalid command. Please enter a valid command. Valid commands are:"
                + " list, unmark, mark, delete, on, todo, deadline, event, remind and bye.";
    }
    private static List prepareList() {
        return new List();
    }

    private static Mark prepareMark(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new PeppaException("Please provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new PeppaException("Please enter a valid task number.");
        }
        return new Mark(index);
    }

    private static Unmark prepareUnmark(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new PeppaException("Please provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new PeppaException("Please enter a valid task number.");
        }
        return new Unmark(index);
    }

    private static On prepareOn(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new PeppaException("Please provide a date in the format: dd/MM/yyyy.");
        }

        String date = parts[1];
        LocalDate parsedDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new PeppaException("Invalid date. Please enter a valid date in the format: dd/MM/yyyy.");
        }
        return new On(parsedDate);
    }

    private static Delete prepareDelete(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new PeppaException("Please provide a task number.");
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (index < 0) {
            throw new PeppaException("Please enter a valid task number.");
        }
        return new Delete(index);
    }

    private static Find prepareFine(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new PeppaException("Please provide a description.");
        }
        return new Find(parts[1]);
    }

    private static Todo prepareTodo(String fullCommand) throws PeppaException {
        String description = fullCommand.substring("todo".length()).trim();

        if (description.isEmpty()) {
            throw new PeppaException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    private static Deadline prepareDeadline(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split("/by");

        if (parts.length < 2) {
            throw new PeppaException("The date of a deadline cannot be empty. Please enter in the format: "
                    + "description /by <date>");
        }

        String description = parts[0].substring("deadline".length()).trim();
        String date = parts[1].trim();

        if (description.isEmpty()) {
            throw new PeppaException("The description of a deadline cannot be empty.");
        }

        try {
            LocalDateTime by = DeadlineTask.parseDateTime(date);
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new PeppaException("Invalid date. Please enter in the format: yyyy-MM-dd HH:mm");
        }
    }

    private static Event prepareEvent(String fullCommand) throws PeppaException {
        String[] parts = fullCommand.split("/from|/to");

        if (parts.length < 3) {
            throw new PeppaException("The start and end date/time of an event cannot be empty. "
                    + "Please enter in the format: description /from <start> /to <end>");
        }

        String description = parts[0].substring("event".length()).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        if (description.isEmpty()) {
            throw new PeppaException("The description of an event cannot be empty.");
        }

        try {
            LocalDateTime fromTime = EventTask.parseDateTime(from);
            return new Event(description, fromTime, to);
        } catch (DateTimeParseException e) {
            throw new PeppaException("Invalid start and end date. Please enter in the format: "
                    + "/from yyyy-MM-dd HH:mm /to: yyyy-MM-dd HH:mm or HH:mm");
        }
    }

    private static Command prepareRemind() {
        return new Remind();
    }
}
