package parser;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * FindCommand class is a command class that finds a task from the list of tasks.
 */
public class FindCommand implements Command {
    @Override
    public String execute(String input, List<Task> items) {
        String findWord = input.substring(4).trim();
        return TaskList.printTasksContainingKeyword(findWord, items);
    }
}
