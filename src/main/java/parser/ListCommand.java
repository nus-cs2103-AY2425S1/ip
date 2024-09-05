package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * ListCommand class created to list all Tasks from current list
 */
public class ListCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        return TaskList.checkList(items, scanner);
    }
}
