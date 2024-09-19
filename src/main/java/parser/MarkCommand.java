
package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * MarkCommand class is a command class that marks a task as done.
 */
public class MarkCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        String numberPart = input.substring(4).trim();
        return TaskList.markTaskAsDone(numberPart, items);
    }
}
