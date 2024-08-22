package util;

import action.Action;
import action.AddTaskAction;
import action.ListTasksAction;
import task.Task;

/**
 * Handles parsing of user input
 *
 * @author dwsc37
 */
public class Parser {
    public Action parseInput(String input) {
        switch (input) {
        case "list":
            return new ListTasksAction();
        default:
            return new AddTaskAction(new Task(input));
        }
    }
}
