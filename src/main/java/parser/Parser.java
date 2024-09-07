package parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.AddCommand;
import command.Command;
import command.CommandType;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.DudeException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     * @throws DudeException If the user input is invalid.
     */
    public static Command parse(String input) throws DudeException {
        String[] tokens = input.split(" ", 2);
        try {
            CommandType commandType = CommandType.valueOf(tokens[0].toUpperCase());
            switch(commandType) {
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
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    throw new DudeException("The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(tokens[1]));
            case DEADLINE:
                String[] deadlineArgs = tokens[1].split(" /by ", 2);
                if (deadlineArgs[0].isEmpty()) {
                    throw new DudeException("The description of a deadline cannot be empty!");
                } else if (deadlineArgs.length < 2) {
                    throw new DudeException("The deadline of a deadline cannot be empty!");
                }
                assert deadlineArgs.length == 2 : "The deadlineArgs array should have length 2";
                try {
                    LocalDateTime by = LocalDateTime.parse(deadlineArgs[1], FORMATTER);
                    return new AddCommand(new Deadline(deadlineArgs[0], by));
                } catch (DateTimeException e) {
                    throw new DudeException("Try using this format for deadline: yyyy-mm-dd HH:mm");
                }
            case EVENT:
                String[] eventArgs = tokens[1].split(" /from | /to ", 3);
                if (eventArgs[0].isEmpty()) {
                    throw new DudeException("The description of an event cannot be empty!");
                } else if (eventArgs.length < 3) {
                    throw new DudeException("The format of timings of the event is wrong!");
                }
                assert eventArgs.length == 3 : "The eventArgs array should have length 3";
                try {
                    LocalDateTime from = LocalDateTime.parse(eventArgs[1], FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(eventArgs[2], FORMATTER);
                    return new AddCommand(new Event(eventArgs[0], from, to));
                } catch (Exception e) {
                    throw new DudeException("Try using this format for the dates: yyyy-mm-dd HH:mm");
                }
            case FIND:
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    throw new DudeException("The keyword to find cannot be empty.");
                }
                return new FindCommand(tokens[1]);
            default:
                return new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DudeException("No argument found!");
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }
}
