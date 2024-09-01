package parser;

import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class ListCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        return TaskList.checkList(items, scanner);
    }
}
