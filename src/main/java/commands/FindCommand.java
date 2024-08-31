package commands;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

public class FindCommand extends Command {
    private final String ITEM_TO_FIND;

    public FindCommand(String item) {
        this.ITEM_TO_FIND = item;
    }
    @Override
    public void execute(TaskList taskList) {
        ArrayList<Task> result = taskList.matchTaskDescription(ITEM_TO_FIND);
        System.out.println("----------");
        result.forEach(task -> System.out.println((result.indexOf(task) + 1) + ". " + task));
        System.out.println("----------");
    }
}
