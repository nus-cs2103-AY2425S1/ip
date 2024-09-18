
package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * UnmarkCommand that helps with unmarking tasks
 */
public class UnmarkCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        String numberPart = input.substring(6).trim();
        return TaskList.unmarkingTask(numberPart, items, scanner);
    }
}
