package parser;

import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class MarkCommand implements Command {
    @Override
    public String execute(String input,List<Task> items, Scanner scanner) {
        //String numberPart = scanner.nextLine().substring(4).trim();
        String numberPart = input.substring(4).trim();
        return TaskList.markingTask(numberPart, items, scanner);
    }
}