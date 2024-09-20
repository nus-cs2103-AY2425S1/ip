package mittens.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    
    protected final ArrayList<Task> tasks;

    /**
     * Initializes a task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the task at the specified index.
     * 
     * @param index The index of the task to get
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     * 
     * @return The number of tasks in the list
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task as done.
     * 
     * @param index The index of the task to mark as done
     * @return The task that was marked as done
     */
    public Task markTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     * 
     * @param index The index of the task to mark as not done
     * @return The task that was marked as not done
     */
    public Task markTaskAsNotDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Deletes a task from the list.
     * 
     * @param index The index of the task to delete
     * @return The task that was deleted
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
