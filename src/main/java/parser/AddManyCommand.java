package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * The class that represents the command to addMany tasks
 */
public class AddManyCommand implements Command {
    @Override
    public String execute(String input, List<Task> tasks, Scanner scanner) {
        return TaskList.addMany(input, tasks, scanner);
    }
}
