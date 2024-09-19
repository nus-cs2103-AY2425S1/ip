package darkpool.tasklist;

import java.util.ArrayList;

import darkpool.task.Task;
import darkpool.DarkpoolException;

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

    public String deleteTask(int index) {
        Task task = getTask(index);
        this.taskList.remove(index);
        return task.toString();
    }

    public String markTask(int index) {
        getTask(index).markDone();
        return getTaskString(index);
    }

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
