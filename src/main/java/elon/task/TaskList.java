package elon.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import elon.ElonException;

/**
 * Represents a list of tasks and provides operations to carry out on them.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the list is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns the task at the specified index from the list.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Checks if the task at the specified index is done.
     *
     * @param index the index of the task to check
     * @return true if the task is done, false otherwise
     */
    public boolean getIsDone(int index) {
        return this.list.get(index).getIsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the index of the task to mark as not done
     */
    public void markNotDone(int index) {
        this.list.get(index).setNotDone();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task to mark as done
     */
    public void markDone(int index) {
        this.list.get(index).setDone();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to add to the list
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param index the index of the task to remove
     */
    public Task removeTask(int index) {
        Task removedTask = list.get(index);
        this.list.remove(index);
        return removedTask;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the list
     */
    public int listSize() {
        return this.list.size();
    }

    /**
     * Finds and returns a list of tasks whose descriptions contain the specified keyword.
     *
     * @param keyword the keyword to find in the tasks' descriptions
     * @return an ArrayList of matching Task objects
     */
    public ArrayList<Task> findTasks(String keyword) {
        return this.list.stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList<Task>::new));
    }

    /**
     * Snoozes a task by updating its date to a new specified date.
     *
     * @param index   the index of the task to snooze
     * @param newDate the new date to set for the task
     * @throws ElonException if the task is a ToDo task
     */

    public void snooze(int index, LocalDateTime newDate) throws ElonException {
        Task taskToSnooze = this.list.get(index);
        if (taskToSnooze instanceof Deadline || taskToSnooze instanceof Event) {
            taskToSnooze.snooze(newDate);
        } else {
            throw new ElonException("A ToDo task cannot be postponed.");
        }
    }
}
