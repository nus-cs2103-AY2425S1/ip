package parser;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;

/**
 * FindCommand class created to find certain tasks in the list
 */
public class FindCommand implements Command {
    @Override
    public String execute(String input, List<Task> items, Scanner scanner) {
        String findWord = input.substring(4).trim();
        return TaskList.find(findWord, items, scanner);
    }
}
