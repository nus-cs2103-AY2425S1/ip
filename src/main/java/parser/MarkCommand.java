
package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * MarkCommand class created to help with Marking specific tasks
 */
public class MarkCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        String numberPart = input.substring(4).trim();
        return TaskList.markingTask(numberPart, items, scanner);
    }
}
