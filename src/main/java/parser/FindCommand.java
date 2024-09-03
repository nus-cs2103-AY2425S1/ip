package parser;

import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class FindCommand implements Command {
    @Override
    public String execute(String input,List<Task> items, Scanner scanner) {
        String findWord = input.substring(4).trim();
        return TaskList.find(findWord, items, scanner);
    }
}
