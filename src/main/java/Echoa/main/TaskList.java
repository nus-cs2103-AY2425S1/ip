package echoa.main;

import echoa.exception.ListOutOfBoundsException;
import echoa.task.Task;

import java.util.ArrayList;

/**
 * This class encapsulates information relating to a tasklist.
 */
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the Task at a particular index.
     *
     * @param index Index is a number reference (0-indexed) in the list of task.
     * @return Task is the task at the particular index in the taskList.
     */
    public Task getSpecificTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of tasks in the taskList.
     *
     * @return number of tasks in the taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds the inputted task into the taskList.
     *
     * @param task task to be added to taskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes the task at the particular index in the list.
     *
     * @param index index of task to be deleted.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void deleteTask(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        this.taskList.remove(index);
    }

    /**
     * Marks the task at the particular index in the list as done.
     *
     * @param index index of the task to be marked as done.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void markTaskAsDone(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        Task task = this.taskList.get(index);
        task.markAsDone();
    }

    /**
     * Unmarks the tasks at the particular index in the list and undone.
     *
     * @param index index of the task to be marked as undone.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void markTaskAsUndone(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        Task task = this.taskList.get(index);
        task.unmarkAsUndone();
    }
}
