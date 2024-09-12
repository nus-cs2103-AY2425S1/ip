package commands;

import java.util.List;

import skibidi.Command;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        List<Task> tasks = storage.getTasks();
        return ui.outputMessage(formatTaskList(tasks));
    }


    /**
     * Formats the list of tasks into a string.
     *
     * @param tasks The list of tasks to format.
     * @return The formatted string of tasks.
     */
    private String formatTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks in your list.";
        }
        StringBuilder formattedTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            formattedTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return formattedTasks.toString();
    }
}
