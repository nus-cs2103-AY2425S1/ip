package commands;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class FilterByDescriptionCommand extends FindCommand {
    private final String itemToFind;


    public FilterByDescriptionCommand(String itemToFind) {
        this.itemToFind = itemToFind;
    }

    @Override
    public String execute(TaskList taskList) {
        StringBuilder resultString = new StringBuilder();

        ArrayList<Task> matchedTasks = taskList.filterTaskByDescription(itemToFind);


        matchedTasks.stream().forEach(task ->
                resultString.append((matchedTasks.indexOf(task) + 1))
                        .append(". ")
                        .append(task)
                        .append("\n")
        );

        return resultString.toString();
    }
}
