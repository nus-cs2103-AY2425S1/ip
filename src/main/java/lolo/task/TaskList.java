package lolo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Lolo application.
 * Provides methods to manage tasks, including adding, deleting, marking as done,
 * and retrieving tasks based on specific criteria.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done.
     */
    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The task that was marked as not done.
     */
    public Task markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Retrieves a list of tasks that are scheduled on the specified date.
     *
     * @param date The date to filter tasks by.
     * @return A TaskList containing tasks scheduled on the specified date.
     */
    public TaskList getTasksOnDate(LocalDateTime date) {
        TaskList tasksOnDate = new TaskList();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).by.toLocalDate().equals(date.toLocalDate())) {
                tasksOnDate.addTask(task);
            } else if (task instanceof Event &&
                    (((Event) task).from.toLocalDate().equals(date.toLocalDate()) ||
                            ((Event) task).to.toLocalDate().equals(date.toLocalDate()))) {
                tasksOnDate.addTask(task);
            }
        }
        return tasksOnDate;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A new {@code TaskList} containing tasks that match the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
