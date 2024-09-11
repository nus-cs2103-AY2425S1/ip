package neko.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import neko.NekoException;
import neko.Storage;
import neko.ui.Ui;

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
        this.tasks = tasks;
    }

    /**
     * Adds task to task list and write the task to storage.
     *
     * @param task The task to be added.
     * @param storage The storage to be written to.
     * @throws IOException if an I/O error occurs.
     */
    public void addTask(Task task, Storage storage) throws IOException {
        tasks.add(task);
        storage.writeFile(task);
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
        } else {
            StringBuilder tasksString = new StringBuilder("Here are the tasks in your list meow:");
            for (int i = 0; i < tasks.size(); i++) {
                tasksString.append("\n" + (i + 1)).append(". ").append(tasks.get(i));
            }
            return tasksString.toString();
        }
    }

    /**
     * Marks the task at the given index as done if it's not marked as done yet.
     * Otherwise, simply return null (if the task is already marked as done).
     *
     * @param index The position where the task is stored.
     * @return The task after marking as done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task markTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Marks the task at the given index as not done if it's marked as done.
     * Otherwise, simply return null (if the task is already marked as not done).
     *
     * @param index The position where the task is stored.
     * @return The task after marking as not done, else return null.
     * @throws NekoException If the index is invalid.
     */
    public Task unmarkTask(int index) throws NekoException {
        checkValidIndex(index);
        if (tasks.get(index).markAsNotDone()) {
            return tasks.get(index);
        } else {
            return null;
        }
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
     * @param index The position where the task is stored.
     * @throws NekoException If the index is invalid.
     */
    public void deleteTask(int index) throws NekoException {
        checkValidIndex(index);
        tasks.remove(index);
    }

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     *
     * @param key The keyword to search for within task descriptions.
     * @return A formatted string of the tasks that match the search keyword. If no tasks are found,
     *      an empty string is returned.
     */
    public String findTasks(String key) {
        String tasksFound = "";
        int numTasksFound = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(key)) {
                tasksFound += ++numTasksFound + "." + task.toString() + "\n";
            }
        }
        return tasksFound.trim();
    }
}
