package darkpool.tasklist;

import java.util.ArrayList;

import darkpool.DarkpoolException;
import darkpool.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return this.taskList.size();
    }

    private String getTaskString(int index) {
        return this.taskList.get(index).toString();
    }

    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The string representation of the task that was deleted.
     */
    public String deleteTask(int index) {
        Task task = getTask(index);
        this.taskList.remove(index);
        return task.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The string representation of the task that was marked as done.
     */
    public String markTask(int index) {
        getTask(index).markDone();
        return getTaskString(index);
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @return The string representation of the task that was marked as undone.
     */
    public String unmarkTask(int index) {
        getTask(index).markUndone();
        return getTaskString(index);
    }

    public String search(String searchQuery) throws DarkpoolException {
        return SearchTask.searchTask(this.taskList, searchQuery);
    }


    @Override
    public String toString() {
        return ToString.toString(taskList);
    }


    public String toFileString() {
        return ToFileString.toFileString(taskList);
    }

}
