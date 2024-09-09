package dook.tasks;

import java.io.IOException;
import java.util.ArrayList;

import dook.DookException;


/**
 * The TaskList class contains the list of all created tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int numOfTasks() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return The Task object that was removed from the TaskList.
     * @throws DookException If the taskNumber is out of range.
     * @throws IOException If an I/O error occurs (not expected in this method).
     */
    public Task delete(int taskNumber) throws DookException, IOException {
        assert taskNumber >= 0 && taskNumber < numOfTasks() : "Task number out of range: " + taskNumber;

        if (taskNumber < 0 || taskNumber > numOfTasks()) {
            throw new DookException("You don't have that many tasks");
        }

        Task removed = this.tasks.remove(taskNumber);
        return removed;
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task getTask(int index) throws DookException {
        assert index >= 0 && index < numOfTasks() : "Index out of range: " + index;
        if (index > numOfTasks()) {
            throw new DookException("You don't have that many tasks");
        }
        return this.tasks.get(index);
    }


}
