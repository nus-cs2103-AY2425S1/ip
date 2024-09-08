package bruno.command;

import bruno.task.Task;
import bruno.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specific keyword in their description.
 */
public class FindCommand extends Command {
    private String keyword;
    private ArrayList<Task> foundTasks;

    /**
     * Constructs a FindCommand object with the specified TaskList and keyword.
     *
     * @param taskList The TaskList that contains the tasks to be searched.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(TaskList taskList, String keyword) {
        super(taskList);
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * Prints the tasks that match the keyword to the user.
     */
    @Override
    public void execute() {
        foundTasks = getTaskList().findTask(keyword);
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (Task task : foundTasks) {
            tasksAsString += task + "\n";
        }

        if (foundTasks.isEmpty()) {
            return "No tasks found with the given keyword";
        }
        return "Here are the matching tasks in your list:\n" + tasksAsString;
    }
}
