package commands;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

public class FindCommand extends Command {
    private final String itemToFind;

    public FindCommand(String item) {
        this.itemToFind = item;
    }

    @Override
    public String execute(TaskList taskList) {
        StringBuilder resultString = new StringBuilder();

        ArrayList<Task> matchedTasks = taskList.matchTaskDescription(itemToFind);

        
        matchedTasks.stream().forEach(task ->
                resultString.append((matchedTasks.indexOf(task) + 1))
                        .append(". ")
                        .append(task)
                        .append("\n")
        );

        return resultString.toString();
    }
}
