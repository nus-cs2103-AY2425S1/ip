
package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * A DeleteCommand class created to delete Tasks from list
 */
public class DeleteCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        int indexToDelete = Integer.parseInt(input.substring(6).trim());
        return TaskList.deleteTask(indexToDelete, items, scanner);
    }
}