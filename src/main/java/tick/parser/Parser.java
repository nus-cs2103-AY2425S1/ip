package tick.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import tick.commands.AddCommand;
import tick.commands.Command;
import tick.commands.CommandType;
import tick.commands.DeleteCommand;
import tick.commands.ExitCommand;
import tick.commands.FindCommand;
import tick.commands.IncorrectCommand;
import tick.commands.ListCommand;
import tick.commands.MarkCommand;
import tick.commands.UnmarkCommand;
import tick.exceptions.TickException;
import tick.tasks.Deadline;
import tick.tasks.Event;
import tick.tasks.ToDo;

/**
 * Represents a parser that parses user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into a command.
     *
     * @param input The user input.
     * @return The command that corresponds to the user input.
     * @throws TickException If the user input is invalid.
     */
    public static Command parse(String input) throws TickException {
        String[] tokens = input.split(" ", 2);
        try {
            CommandType commandWord = CommandType.valueOf(tokens[0].toUpperCase());
            switch (commandWord) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(Integer.parseInt(tokens[1]) - 1);
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(tokens[1]) - 1);
            case DELETE:
                return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
            case TODO:
                return Parser.prepareTodo(tokens[1]);
            case DEADLINE:
                return Parser.prepareDeadline(tokens[1]);
            case EVENT:
                return Parser.prepareEvent(tokens[1]);
            case FIND:
                return Parser.prepareFind(tokens[1]);
            default:
                return new IncorrectCommand();
            }
        } catch (IllegalArgumentException e) {
            throw new TickException("I don't know what that means!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TickException("Please specify an argument!");
        }
    }

    private static Command prepareTodo(String args) throws TickException {
        if (args.isEmpty()) {
            throw new TickException("The description of a todo cannot be empty.");
        }
        ToDo task = new ToDo(args);
        return new AddCommand(task);
    }

    private static Command prepareDeadline(String args) throws TickException {
        String[] parts = args.split(" /by ", 2);
        if (parts.length < 2) {
            throw new TickException("Please specify the deadline task in this format:"
                    + " <description> /by <deadline>.");
        }
        assert parts.length == 2 : "Deadline task should have exactly 2 arguments.";
        try {
            LocalDate by = LocalDate.parse(parts[1]);
            Deadline task = new Deadline(parts[0], by);
            return new AddCommand(task);
        } catch (DateTimeException e) {
            throw new TickException("Please specify the deadline in this format: yyyy-mm-dd.");
        }
    }

    private static Command prepareEvent(String args) throws TickException {
        String[] parts = args.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new TickException("Please specify the event task in this format:"
                    + " <description> /from <start> /to <end>.");
        }
        assert parts.length == 3 : "Event task should have exactly 3 arguments.";
        try {
            LocalDate from = LocalDate.parse(parts[1]);
            LocalDate to = LocalDate.parse(parts[2]);
            Event task = new Event(parts[0], from, to);
            return new AddCommand(task);
        } catch (DateTimeException e) {
            throw new TickException("Please specify the dates in this format: yyyy-mm-dd.");
        }
    }

    private static Command prepareFind(String args) throws TickException {
        if (args.isEmpty()) {
            throw new TickException("The keyword to search for cannot be empty.");
        }
        return new FindCommand(args);
    }
}
