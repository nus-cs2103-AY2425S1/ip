package rasputin.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the form of an ArrayList.
 *
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns whether the TaskList is empty.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the TaskList.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the given index.
     *
     * @param index of task to be retrieved.
     * @return Task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index of task to be marked.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index of task to be unmarked
     */
    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Adds the task to the TaskList.
     *
     * @param task to be added.
     */
    public void add(Task task) {
        assert task != null : "Tasks cannot be null";
        tasks.add(task);
    }

    /**
     * Removes the task from the TaskList.
     *
     * @param index of task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the contents of the list to be printed out in the UI.
     * If list is empty, return "No tasks in list!".
     *
     * @return String format of the list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks in list!";
        }
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += String.format("%d.%s", i + 1, tasks.get(i));
            if (i < tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
