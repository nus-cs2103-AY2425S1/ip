
package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * UnmarkCommand class is a command class that unmarks a task as done.
 */
public class UnmarkCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        String numberPart = input.substring(6).trim();
        return TaskList.unmarkTaskAsDone(numberPart, items);
    }
}
