package Commands;

import Task.TaskList;
import Task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String ITEM_TO_FIND;

    public FindCommand(String itemToFind) {
        this.ITEM_TO_FIND = itemToFind;
    }
    @Override
    public void execute(TaskList taskList) {
        ArrayList<Task> result = taskList.matchTaskDescription(ITEM_TO_FIND);
        System.out.println("----------");
        result.forEach(task -> System.out.println((result.indexOf(task) + 1) + ". " + task));
        System.out.println("----------");
    }
}
