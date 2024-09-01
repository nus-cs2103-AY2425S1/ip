package parser;

import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public interface Command {
    String execute(String input, List<Task> items, Scanner scanner);
}
