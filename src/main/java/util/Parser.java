package util;

import action.Action;
import action.AddTaskAction;
import action.ListTasksAction;
import action.MarkTaskAction;
import action.UnmarkTaskAction;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Handles parsing of user input
 *
 * @author dwsc37
 */
public class Parser {
    public Action parseInput(String input) {
        String command  = input.split(" ")[0];
        switch (command) {
        case "list":
            return new ListTasksAction();
        case "mark":
            return new MarkTaskAction(parseTaskIndex(input) - 1);
        case "unmark":
            return new UnmarkTaskAction(parseTaskIndex(input) - 1);
        case "todo":
            Todo todo = parseTodo(input);
            return new AddTaskAction(todo);
        case "deadline":
            Deadline deadline = parseDeadline(input);
            return new AddTaskAction(deadline);
        case "event":
            Event event = parseEvent(input);
            return new AddTaskAction(event);
        default:
            // TODO: add error handling for unrecognised commands
            return null;
        }
    }

    // TODO: add error handling for all parsers
    private int parseTaskIndex(String input) {
        String[] arr = input.split(" ");
        return Integer.parseInt(arr[1]);
    }

    private Todo parseTodo(String input) {
        String arg = input.substring(5);
        return new Todo(arg);
    }

    private Deadline parseDeadline(String input) {
        String[] args = input.substring(9).split(" /by ");

        return new Deadline(args[0], args[1]);
    }

    private Event parseEvent(String input) {
        String[] args = input.substring(6).split(" /from ");
        String[] range = args[1].split(" /to ");

        return new Event(args[0], range[0], range[1]);
    }
}
