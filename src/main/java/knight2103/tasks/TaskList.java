package knight2103.tasks;

import java.util.ArrayList;

/**
 * Models a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an object containing an empty list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs an object containing an existent list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList(ArrayList<Task> storageData) {
        this.tasks = storageData;
    }

    /**
     * Returns the number of tasks in the bot's taskList.
     *
     * @return Number of tasks in the bot's taskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the Task specified by the index in the bot's taskList.
     *
     * @return the Task specified by index in the bot's taskList.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task into the bot's taskList.
     *
     * @param newTask Task to be added to the taskList.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Returns the task that is being marked as done. The task identified by its index
     * will be marked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be marked.
     * @return The newly marked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task mark(int index) throws IndexOutOfBoundsException { // modify Command
        tasks.get(index).markDone();
        return tasks.get(index); // must be after markDone to return the newly updated one
    }

    /**
     * Returns the task that is being unmarked as done. The task identified by its index
     * will be unmarked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be unmarked.
     * @return The newly unmarked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task unmark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmarkDone();
        return tasks.get(index); // must be after unmarkDone to return the newly updated one
    }

    /**
     * Returns the task that is being deleted. The task identified by its index
     * will be deleted in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be deleted.
     * @return The Task to be deleted.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task taskToDelete = tasks.get(index);
        tasks.remove(index);
        return taskToDelete;
    }
}
