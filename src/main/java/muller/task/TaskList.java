package muller.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import muller.command.MullerException;

/**
 * Represents the list of tasks. Provides methods to manage tasks, including adding, deleting, and retrieving tasks.
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
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param index The index of the task to delete.
     * @throws MullerException If the index is out of bounds.
     */
    public void deleteTask(int index) throws MullerException {
        if (index < 0 || index >= tasks.size()) {
            throw new MullerException("Invalid task number!");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws MullerException If the index is out of bounds.
     */
    public Task get(int index) throws MullerException {
        if (index < 0 || index >= tasks.size()) {
            throw new MullerException("Invalid task number!");
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks that are due within the given date range.
     *
     * @param from The start date (usually today).
     * @param to   The end date (e.g., 7 days from today).
     * @return A list of tasks that are due within the specified date range.
     */
    public List<Task> getTasksDueSoon(LocalDate from, LocalDate to) {
        List<Task> upcomingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof DeadlineTask) {
                LocalDate deadline = ((DeadlineTask) task).getDeadline();
                if (deadline != null && (deadline.isEqual(from) || (deadline.isAfter(from) && deadline.isBefore(to)))) {
                    upcomingTasks.add(task);
                }
            } else if (task instanceof EventTask) {
                LocalDate startDate = ((EventTask) task).getStartDate();
                if (startDate != null
                        && (startDate.isEqual(from) || (startDate.isAfter(from) && startDate.isBefore(to)))) {
                    upcomingTasks.add(task);
                }
            }
        }
        return upcomingTasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

