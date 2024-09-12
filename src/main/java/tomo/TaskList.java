package tomo;

import task.Task;
import exception.TaskListException;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the list of tasks
 */

public class TaskList {
    private List<Task> tasks;
    
    /**
     * Constructor for TaskList
     */
    public TaskList() { this.tasks = new ArrayList<>(); }

    /**
     * Returns the size of the task list
     * 
     * @return Size of the task list 
     */
    public int size() { return tasks.size(); }

    /**
     * Gets task at a specific index in the task list
     * 
     * @param i The index to be retrieve
     * @return The task at the given index
     */
    public Task get(int i) { return tasks.get(i); }

    /**
     * Updates the task at a specific index
     * 
     * @param i The index of the task to be changed
     * @param task The replacing task at the given index
     */
    private void set(int i, Task task) { tasks.set(i, task); }

    /**
     * Adds a new task to the task
     * 
     * @param task The new task
     */
    public void add(Task task) { tasks.add(task); }

    /**
     * Marks a task as done
     * 
     * @param i index of the task to be marked
     * @return The marked task
     * @throws TaskListException If the given index is out of bound
     */
    public Task mark(int i) throws TaskListException {
        if (i < 0 || i >= tasks.size()) {
            throw new TaskListException("Idx out of bound for mark command");
        }
        Task task = get(i);
        task.mark();
        set(i, task);
        return task;
    }

    /**
     * Marks a task as undone
     * 
     * @param i index of the task to be unmarked
     * @return The unmarked task
     * @throws TaskListException If the the given index is out of bound
     */
    public Task unmark(int i) throws TaskListException {
        if (i < 0 || i >= tasks.size()) {
            throw new TaskListException("Idx out of bound for unmark command");
        }
        Task task = get(i);
        task.unmark();
        set(i, task);
        return task;
    }

    /**
     * Deletes a task
     * 
     * @param i The index of the task to be deleted
     * @return The deleted task
     * @throws TaskListException If the given index is out of bound
     */
    public Task delete(int i) throws TaskListException {
        if (i < 0 || i >= tasks.size()) {
            throw new TaskListException("Idx out of bound for delete command");
        }
        Task task = tasks.get(i);
        tasks.remove(i); 
        return task;
    }

    public TaskList find(String pattern) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.toString().contains(pattern)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
};