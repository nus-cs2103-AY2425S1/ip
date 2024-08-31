package nuffle.task;

import java.util.ArrayList;

/**
 * This class represents a task list which manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> inputList;

    /**
     * Construct a new TaskList.
     */
    public TaskList() {


        this.inputList = new ArrayList<>();
    }

    /**
     * Construct a TaskList object with the given list of tasks.
     * @param inputList The list of tasks to manage.
     */
    public TaskList(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        inputList.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     * @param index The index of the corresponding task to be deleted.
     * @return The removed task object.
     */
    public Task deleteTask(int index) {
        return inputList.remove(index);
    }

    /**
     * Get a task from the TaskList.
     * @param index The index of the corresponding task to be retrieved.
     * @return The retrieved task object.
     */
    public Task getTask(int index) {
        return inputList.get(index);
    }

    /**
     * Get the length of the TaskList.
     * @return The length of the TaskList.
     */
    public int getSize() {
        return inputList.size();
    }

    /**
     * Get the TaskList itself
     * @return The TaskList
     */
    public ArrayList<Task> getInputList() {
        return inputList;
    }
}
