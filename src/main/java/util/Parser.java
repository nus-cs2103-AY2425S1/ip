package util;

import action.Action;
import action.AddTaskAction;
import action.HelpAction;
import action.ListTasksAction;
import action.MarkTaskAction;
import action.UnmarkTaskAction;
import exception.BotException;
import exception.InvalidCommandException;
import exception.InvalidCommandFormatException;
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
        String command  = input.split(" ")[0];
        switch (command) {
        case "list":
            return new ListTasksAction();
        case "mark":
            return new MarkTaskAction(parseTaskIndex(input));
        case "unmark":
            return new UnmarkTaskAction(parseTaskIndex(input));
        case "todo":
            Todo todo = parseTodo(input);
            return new AddTaskAction(todo);
        case "deadline":
            Deadline deadline = parseDeadline(input);
            return new AddTaskAction(deadline);
        case "event":
            Event event = parseEvent(input);
            return new AddTaskAction(event);
        case "help":
            return new HelpAction();
        default:
            throw new InvalidCommandException(input);
        }
    }

    private int parseTaskIndex(String input) throws InvalidCommandFormatException, InvalidTaskIndexException {
        String[] arr = input.split(" ");
        if (arr.length != 2) {
            throw new InvalidCommandFormatException(arr[0]);
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
            throw new InvalidCommandFormatException("todo");
        }
        return new Todo(arg.strip());
    }

    private Deadline parseDeadline(String input) throws InvalidCommandFormatException {
        String[] args = input.substring(8).split(" /by ");
        if (args.length != 2 || args[0].isBlank() || args[1].isBlank()) {
            throw new InvalidCommandFormatException("deadline");
        }
        return new Deadline(args[0].strip(), args[1].strip());
    }

    private Event parseEvent(String input) throws InvalidCommandFormatException {
        String[] args = input.substring(5).split(" /from ");
        if (args.length != 2 || args[0].isBlank()) {
            throw new InvalidCommandFormatException("event");
        }
        String[] range = args[1].split(" /to ");
        if (range.length != 2 || range[0].isBlank() || range[1].isBlank()) {
            throw new InvalidCommandFormatException("event");
        }
        return new Event(args[0].strip(), range[0].strip(), range[1].strip());
    }
}
