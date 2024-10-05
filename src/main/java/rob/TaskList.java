package rob;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks the user has added.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int len() {
        return taskList.size();
    }

    /**
     * Gets a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.get(index);
        } else {
            System.out.println("Invalid index: " + index);
            return null;
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void removeTask(int i) {
        taskList.remove(i);
    }

    /**
     * Searches for tasks containing the specified keyword.
     * Returns a list of tasks that contain the keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the specified keyword in their descriptions.
     * @throws RobException If an error occurs while processing the tasks.
     */
    public List<Task> searchTasks(String keyword) throws RobException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            String line = task.toSaveString();
            String[] parts = line.split(" \\| ", 5);
            String desc = parts[2].trim();

            if (desc.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Searches for the exact tasks that matches the specified keyword.
     * Returns a list of tasks that matches the keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that matches the specified keyword in their descriptions.
     * @throws RobException If an error occurs while processing the tasks.
     */
    public List<Task> searchExactTasks(String keyword) throws RobException {
        List<Task> exactMatchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            String line = task.toSaveString();
            String[] parts = line.split(" \\| ", 5);
            String desc = parts[2].trim();

            if (desc.equals(keyword)) {
                exactMatchingTasks.add(task);
            }
        }
        return exactMatchingTasks;
    }
}
