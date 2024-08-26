package killua.util;

import killua.task.Deadline;
import killua.task.Event;
import killua.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Killua task manager.
 * It provides methods to add, delete, mark, and unmark tasks,
 * as well as to retrieve tasks based on specific criteria.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as not done yet at the specified index.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Returns a TaskList containing tasks that are scheduled for a specific date.
     *
     * @param date The date for which tasks are to be retrieved.
     * @return A TaskList containing tasks on the specified date.
     */
    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();

        for (Task task : tasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getDate().equals(date)) {
                    tasksOnDate.addTask(task);
                }
            } else if (task instanceof Event event) {
                if (!event.getStartDate().isAfter(date) && !event.getEndDate().isBefore(date)) {
                    tasksOnDate.addTask(task);
                }
            }
        }

        return tasksOnDate;
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
     * Prints all tasks in the task list with their respective index.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }
}
