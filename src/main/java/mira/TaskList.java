package mira;

import java.util.ArrayList;

/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    /* Array to store tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to be managed by this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to delete (1-based).
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Lists all the tasks currently stored in the task list.
     *
     * @return The string representation of the entire task list.
     */
    public String listTasks() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksList.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                tasksList.append("\n"); // Add newline only if it is not the last task
            }
        }
        return tasksList.toString();
    }

    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to mark as done (1-based index).
     */
    public void markTask(int index) {
        this.tasks.get(index - 1).setStatus(true);
    }

    /**
     * Unmarks the specified task, setting it back to not done.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public void unmarkTask(int index) {
        this.tasks.get(index - 1).setStatus(false);
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     *
     * <p>This method searches through the task list and collects all tasks that have the keyword
     * in their descriptions. If no tasks are found, it returns a message indicating that no tasks
     * matched the keyword. Otherwise, it returns a formatted string listing the matching tasks.</p>
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A formatted string listing the tasks that match the keyword, or a message indicating
     *         that no tasks were found.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No tasks found with the keyword: " + keyword;
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(i + 1).append(".").append(matchingTasks.get(i));
            if (i < matchingTasks.size() - 1) {
                result.append("\n"); // Add newline only if it is not the last task
            }
        }

        return result.toString();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index).
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index less than 1 || index larger than size()).
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}
