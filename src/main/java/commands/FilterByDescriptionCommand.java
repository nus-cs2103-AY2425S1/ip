package commands;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

/**
 * Represents a command to filter tasks by their description.
 * Extends the {@code FindCommand} class and provides functionality to search for tasks
 * that contain the specified description keyword.
 */
public class FilterByDescriptionCommand extends FindCommand {
    private final String itemToFind;

    /**
     * Constructs a {@code FilterByDescriptionCommand} with the specified item to find in the task descriptions.
     *
     * @param itemToFind The keyword or phrase to search for in task descriptions.
     */
    public FilterByDescriptionCommand(String itemToFind) {
        this.itemToFind = itemToFind;
    }

    /**
     * Executes the filter command, searching for tasks in the task list that contain the specified keyword
     * in their descriptions. Returns a string representation of the matched tasks.
     *
     * @param taskList The task list to search through for matching tasks.
     * @return A string representation of all tasks that contain the specified description keyword.
     */
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
