package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * ListCommand class is a command class that lists all the tasks in the list of tasks.
 */
public class ListCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        return TaskList.checkListOfTasks(items);
    }
}
