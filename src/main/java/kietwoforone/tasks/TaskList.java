package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.util.ArrayList;

/**
 * Represents and ArrayList of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList object.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList.
     *
     * @return Task ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a new task to the ArrayList.
     *
     * @param newTask
     */
    public void addTasks(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Removes a task from the ArrayList and returns the task.
     *
     * @param position
     * @return Task.
     * @throws KieTwoForOneException
     */
    public Task deleteTask(int position) throws KieTwoForOneException {
        try {
            return this.tasks.remove(position - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new KieTwoForOneException(String.format("No task at index %d!", position));
        }
    }

    /**
     * Returns a task string with the task marked.
     *
     * @param position
     * @return Task string.
     * @throws KieTwoForOneException
     */
    public String markTask(int position) throws KieTwoForOneException {
        try {
            return this.tasks.get(position - 1).markTask();
        } catch (IndexOutOfBoundsException e) {
            throw new KieTwoForOneException("Task does not exist!");
        }
    }

    /**
     * Returns a task string with the task unmarked.
     *
     * @param position
     * @return Task string.
     * @throws KieTwoForOneException
     */
    public String unmarkTask(int position) throws KieTwoForOneException {
        try {
            return this.tasks.get(position - 1).unmarkTask();
        } catch (IndexOutOfBoundsException e) {
            throw new KieTwoForOneException("Task does not exist!");
        }
    }

}
