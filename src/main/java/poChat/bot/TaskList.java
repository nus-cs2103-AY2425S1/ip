package poChat.bot;

import poChat.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listTasks;

    public TaskList() {
        this.listTasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listTasks) {
        this.listTasks = listTasks;
    }

    /**
     * Adds this task to the TaskList
     * @param task: the task to be added to the TaskList
     */

    public void add(Task task) {
        this.listTasks.add(task);
    }

    /**
     *
     * @return size of the TaskList as an <code>int</code>
     */

    public int size() {
        return this.listTasks.size();
    }

    /**
     * Removes this task from the TaskList
     * @param task: the task to be removed from the TaskList
     */
    public void remove(Task task) {
        this.listTasks.remove(task);
    }

    /**
     * Returns the Task at this index
     * @param index: the index where the task is located
     * @return task at index
     */
    public Task get(int index) {
        return this.listTasks.get(index);
    }

    /**
     *
     * @return the tasks in an ArrayList format
     */
    public ArrayList<Task> toList() {
        return this.listTasks;
    }
}
