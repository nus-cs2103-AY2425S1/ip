package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * A ChangePriorityCommand to change priority for various task instances
 */
public class ChangePriorityCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        return TaskList.changePriorityForSpecificTask(input, items, scanner);
    }
}
