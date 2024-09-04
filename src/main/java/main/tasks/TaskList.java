package main.tasks;

import java.util.ArrayList;

/**
 * TaskList represents an arraylist of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasksArray;

    /**
     * A second constructor for TaskList.
     * Used to intialise a new TaskList with data from a previous TaskList.
     * @param taskList
     */
    public TaskList(TaskList taskList) {
        this.tasksArray = new ArrayList<Task>(taskList.tasksArray);
    }

    /**
     * A default constructor for TaskList.
     */
    public TaskList() {
        this.tasksArray = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasksArray.add(task);
    }

    public Task get(int index) {
        return tasksArray.get(index);
    }

    public int size() {
        return tasksArray.size();
    }

    public void remove(int index) {
        tasksArray.remove(index);
    }

    public boolean isEmpty() {
        return tasksArray.isEmpty();
    }
}
