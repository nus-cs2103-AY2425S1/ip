package parser;

import command.*;
import exception.DudeException;
import task.Deadline;
import task.Event;
import task.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String input) throws DudeException {
        String[] tokens = input.split(" ", 2);
        try {
            CommandType commandType = CommandType.valueOf(tokens[0].toUpperCase());
            switch (commandType) {
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
                if (deadlineArgs[0].isEmpty() ) {
                    throw new DudeException("The description of a deadline cannot be empty!");
                } else if (deadlineArgs.length < 2) {
                    throw new DudeException("The deadline of a deadline cannot be empty!");
                }
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
                } else if (eventArgs.length < 3 ) {
                    throw new DudeException("The format of timings of the event is wrong!");
                }
                try {
                    LocalDateTime from = LocalDateTime.parse(eventArgs[1], FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(eventArgs[2], FORMATTER);
                    return new AddCommand(new Event(eventArgs[0], from, to));
                } catch (Exception e) {
                    throw new DudeException("Try using this format for the dates: yyyy-mm-dd HH:mm");
                }
            default:
                return new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DudeException("No argument found!");
        } catch (IllegalArgumentException e) {
            throw new DudeException("I'm sorry, but I don't know what that means :(");
        }
    }
}
