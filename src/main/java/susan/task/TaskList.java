package susan.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list which contains the tasks added by the user.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append("\n");
            list.append(" ").append(i + 1).append(".").append(tasks.get(i));
        }
        return list.toString();
    }

    /**
     * Finds tasks in the list that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return TaskList (A list of tasks) that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
