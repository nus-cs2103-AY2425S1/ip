package jeriel.util;
import jeriel.command.*;
import jeriel.task.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the given index.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the given index in the task list.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return the list of tasks in the task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
