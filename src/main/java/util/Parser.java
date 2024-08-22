package util;

import action.Action;
import action.AddTaskAction;
import action.ListTasksAction;
import action.MarkTaskAction;
import action.UnmarkTaskAction;
import task.Task;

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
            int taskIndex = parseTaskIndex(input);
            return new UnmarkTaskAction(parseTaskIndex(input) - 1);
        default:
            return new AddTaskAction(new Task(input));
        }
    }

    private int parseTaskIndex(String input) {
        String[] arr = input.split(" ");
        return Integer.parseInt(arr[1]);
    }
}
