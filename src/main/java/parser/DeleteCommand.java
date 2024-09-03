package parser;

import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        int indexToDelete = Integer.parseInt(input.substring(6).trim());
        return TaskList.deleteTask(indexToDelete, items, scanner);
    }
}