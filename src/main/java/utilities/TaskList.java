package utilities;

import tasks.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}