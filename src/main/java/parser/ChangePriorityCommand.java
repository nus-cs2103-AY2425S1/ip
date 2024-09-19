package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * ChangePriorityCommand class is a command class that changes the priority of a specific task.
 */
public class ChangePriorityCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        return TaskList.changePriorityForSpecificTask(input, items);
    }
}
