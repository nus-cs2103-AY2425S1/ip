package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import action.Action;
import action.AddTaskAction;
import action.DeleteTaskAction;
import action.HelpAction;
import action.ListTasksAction;
import action.MarkTaskAction;
import action.UnmarkTaskAction;
import enums.Command;
import exception.BotException;
import exception.InvalidCommandException;
import exception.InvalidCommandFormatException;
import exception.InvalidDateFormatException;
import exception.InvalidTaskIndexException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Handles parsing of user input
 *
 * @author dwsc37
 */
public class Parser {
    public Action parseInput(String input) throws BotException {
        Command command  = Command.getCommandFromInput(input.split(" ")[0]);
        return switch (command) {
            case LIST -> new ListTasksAction();
            case TODO -> {
                Todo todo = parseTodo(input);
                yield new AddTaskAction(todo);
            }
            case DEADLINE -> {
                Deadline deadline = parseDeadline(input);
                yield new AddTaskAction(deadline);
            }
            case EVENT -> {
                Event event = parseEvent(input);
                yield new AddTaskAction(event);
            }
            case MARK -> new MarkTaskAction(parseTaskIndex(input));
            case UNMARK -> new UnmarkTaskAction(parseTaskIndex(input));
            case DELETE -> new DeleteTaskAction(parseTaskIndex(input));
            case HELP -> new HelpAction();
            default -> throw new InvalidCommandException(input);
        };
    }

    private int parseTaskIndex(String input) throws BotException {
        String[] arr = input.split(" ");
        if (arr.length != 2) {
            throw new InvalidCommandFormatException(Command.getCommandFromInput(arr[0]));
        }

        try {
            int taskIndex = Integer.parseInt(arr[1]);
            if (taskIndex <= 0) {
                throw new InvalidTaskIndexException(arr[1]);
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(arr[1]);
        }
    }

    private Todo parseTodo(String input) throws InvalidCommandFormatException {
        String arg = input.substring(4);
        if (arg.isBlank()) {
            throw new InvalidCommandFormatException(Command.TODO);
        }
        return new Todo(arg.strip());
    }

    private Deadline parseDeadline(String input) throws InvalidCommandFormatException, InvalidDateFormatException {
        String[] args = input.substring(8).split(" /by ");
        if (args.length != 2 || args[0].isBlank() || args[1].isBlank()) {
            throw new InvalidCommandFormatException(Command.DEADLINE);
        }
        try {
            return new Deadline(args[0].strip(), LocalDate.parse(args[1].strip()));
        } catch (DateTimeParseException e){
            throw new InvalidDateFormatException(e.getParsedString());
        }
    }

    private Event parseEvent(String input) throws InvalidCommandFormatException, InvalidDateFormatException {
        String[] args = input.substring(5).split(" /from ");
        if (args.length != 2 || args[0].isBlank()) {
            throw new InvalidCommandFormatException(Command.EVENT);
        }
        String[] range = args[1].split(" /to ");
        if (range.length != 2 || range[0].isBlank() || range[1].isBlank()) {
            throw new InvalidCommandFormatException(Command.EVENT);
        }
        try {
            return new Event(args[0].strip(), LocalDate.parse(range[0].strip()), LocalDate.parse(range[1].strip()));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e.getParsedString());
        }
    }
}
