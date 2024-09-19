
package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * DeleteCommand class is a command class that deletes a task from the list of tasks.
 */
public class DeleteCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        int indexToDelete = Integer.parseInt(input.substring(6).trim());
        return TaskList.deleteTask(indexToDelete, items);
    }
}
