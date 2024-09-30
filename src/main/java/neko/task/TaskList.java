package neko.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import neko.NekoException;

/**
 * The TaskList class represents a task list that stores tasks added by the user,
 * using an ArrayList to manage the tasks.
 *
 * <p>It provides functionalities to add, list, delete, and mark/unmark tasks in the list.
 * Tasks can be of different types, such as Todo, Deadline, or Event.</p>
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null!";
        this.tasks = tasks;
    }

    /**
     * Adds task to task list and write the task to storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task stored at the index.
     * @throws NekoException If the index is invalid.
     */
    public Task getTask(int index) throws NekoException {
        checkValidIndex(index);
        Task task = tasks.get(index);
        assert task != null : "Task should not be null";
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Outputs the string representation of the tasks in the list if there is
     * at least one task in the list. Otherwise, output an error message.
     *
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "You don't have any tasks yet meow!";
        }
        StringBuilder tasksString = new StringBuilder("Here are the tasks in your list meow:");
        for (int i = 0; i < tasks.size(); i++) {
            tasksString.append("\n" + (i + 1)).append(". ").append(tasks.get(i));
        }
        assert !tasksString.isEmpty() : "Return string should not be empty";
        return tasksString.toString();
    }

    /**
     * Marks the tasks at the given indices as done if they were not marked as done yet.
     * Otherwise, simply return null (if the task is already marked as done).
     *
     * @param indices The position where the tasks are stored.
     * @return The task after marking as done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task[] markTask(Integer... indices) throws NekoException {
        Task[] markedTasks = new Task[indices.length];
        int counter = 0;
        for (int index : indices) {
            checkValidIndex(index);
            Task task = tasks.get(index);
            if (task.markAsDone()) {
                markedTasks[counter] = task;
                assert task.isDone() : "Task should be marked as done";
                counter++;
            }
        }
        return markedTasks;
    }

    /**
     * Marks the tasks at the given indices as not done if they were marked as done.
     * Otherwise, simply return null (if the task is already marked as not done).
     *
     * @param indices The position where the tasks are stored.
     * @return The task after marking as not done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task[] unmarkTask(Integer... indices) throws NekoException {
        Task[] unmarkedTasks = new Task[indices.length];
        int counter = 0;
        for (int index : indices) {
            checkValidIndex(index);
            Task task = tasks.get(index);
            if (task.markAsNotDone()) {
                unmarkedTasks[counter] = task;
                assert !task.isDone() : "Task should be marked as not done";
                counter++;
            }
        }
        return unmarkedTasks;
    }

    /**
     * Checks if the index is valid, i.e. greater or equal to 0, smaller than the
     * size of the list, and whether the task is empty, and throw an exception
     * with the corresponding message.
     *
     * @param index The index where the task is stored in the list.
     * @throws NekoException If the index is invalid.
     */
    protected void checkValidIndex(int index) throws NekoException {
        if (index < 0) {
            throw new NekoException("Invalid task number meow!");
        }
        if (tasks.isEmpty()) {
            throw new NekoException("You don't have any tasks yet meow!");
        }
        if (index >= tasks.size()) {
            throw new NekoException("You only have " + tasks.size() + " tasks now meow!");
        }
    }

    /**
     * Deletes the task at the given index if the index is valid.
     * Otherwise, do nothing.
     *
     * @param indices The positions where the tasks are stored.
     * @throws NekoException If the index is invalid.
     */
    public Task[] deleteTask(Integer... indices) throws NekoException {
        Task[] deletedTasks = new Task[indices.length];
        int counter = 0;
        Arrays.sort(indices, (x, y) -> Integer.compare(y, x));
        for (int index : indices) {
            checkValidIndex(index);
            deletedTasks[counter++] = tasks.get(index);
            tasks.remove(index);
        }
        return deletedTasks;
    }

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     *
     * @param key The keyword to search for within task descriptions.
     * @return A formatted string of the tasks that match the search keyword. If no tasks are found,
     *      an empty string is returned.
     */
    public String findTasks(String key) throws NekoException {
        if (key.isEmpty()) {
            throw new NekoException("Keyword cannot be empty");
        }
        String tasksFound = "";
        int numTasksFound = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(key)) {
                tasksFound += ++numTasksFound + "." + task.toString() + "\n";
            }
        }

        return tasksFound.trim();
    }

    /**
     * Returns a list of tasks scheduled on the specified date.
     *
     * @param date The date for which tasks are to be viewed.
     * @return A string containing the tasks scheduled on the given date.
     *         If there are no tasks, a message indicating no tasks is returned.
     */

    public String viewTasksOnDate(LocalDate date) {
        String dateStr = date.format(DateTimeFormatter.ofPattern("eee, d MMM uuuu"));
        if (tasks.isEmpty()) {
            return "You don't have any tasks yet meow!";
        }
        String prefix = "Meow! Here is your schedule on " + dateStr + "\n";
        StringBuilder schedule = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            if (task.isOnDate(date)) {
                schedule.append(count++ + ". " + task.getScheduleStr(date) + "\n");
            }
        }
        if (schedule.toString().isEmpty()) {
            return "Meow! You dont have any task on " + dateStr;
        }
        return prefix + schedule.toString().trim();
    }
}
