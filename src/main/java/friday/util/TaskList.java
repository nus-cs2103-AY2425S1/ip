package friday.util;

import java.time.LocalDate;
import java.util.ArrayList;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;

/**
 * The TaskList class manages a list of tasks. It provides methods to add, delete, mark, and unmark tasks,
 * as well as retrieve the list of tasks and check its status.
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
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
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
     * Deletes a task from the TaskList by its index.
     *
     * @param taskIndex The index of the task to delete. Must be within the bounds of the task list.
     * @return The task that was removed.
     * @throws AssertionError if the taskIndex is out of bounds.
     */
    public Task deleteTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index should be within bounds";
        return tasks.remove(taskIndex);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param taskIndex The index of the task to mark as done. Must be within the bounds of the task list.
     * @return The task that was marked as done.
     * @throws AssertionError if the taskIndex is out of bounds.
     */
    public Task markTaskAsDone(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index should be within bounds";
        Task markedTask = tasks.get(taskIndex);
        markedTask.markAsDone();
        return markedTask;
    }

    /**
     * Unmarks a task as done by its index.
     *
     * @param taskIndex The index of the task to unmark as done. Must be within the bounds of the task list.
     * @return The task that was unmarked as done.
     * @throws AssertionError if the taskIndex is out of bounds.
     */
    public Task unmarkTaskAsDone(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index should be within bounds";
        Task unmarkedTask = tasks.get(taskIndex);
        unmarkedTask.unmarkAsDone();
        return unmarkedTask;
    }

    /**
     * Finds tasks that occur on the specified date. This includes tasks with deadlines on that date
     * and events that occur on or span across that date.
     *
     * @param date The date to filter tasks by.
     * @return A TaskList containing the tasks that occur on the specified date.
     */
    public TaskList findTasksByDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getBy().equals(date)) {
                    matchingTasks.add(task);
                }
            } else if (task instanceof Event) {
                LocalDate from = ((Event) task).getFrom();
                LocalDate to = ((Event) task).getTo();
                if ((date.equals(from) || date.equals(to)) || (date.isAfter(from) && date.isBefore(to))) {
                    matchingTasks.add(task);
                }
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A TaskList containing the tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }
}
