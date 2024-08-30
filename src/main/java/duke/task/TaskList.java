package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that can be manipulated by adding, deleting, marking, and finding tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks to populate the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
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
     * Deletes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unmarkTaskAsDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return A List of tasks in the TaskList.
     */
    public List<Task> getTasksList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a list of tasks that occur on the specified date.
     *
     * @param date The date to filter tasks by.
     * @return A List of tasks occurring on the specified date.
     */
    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A List of tasks that match the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}