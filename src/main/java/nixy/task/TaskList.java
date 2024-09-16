package nixy.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nixy.Storage;
import nixy.exceptions.TaskNotFoundException;

/**
 * Class that manages tasks as a collection.
 * It provides basic functionality for adding, deleting, listing, and marking tasks as done.
 */
public class TaskList {
    private Storage storage;
    private List<Task> tasks;

    /**
     * Inner class that provides an iterator for reading tasks.
     */
    private class TaskIterator implements Iterator<ReadableTask> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < tasks.size();
        }

        @Override
        public ReadableTask next() {
            return tasks.get(index++);
        }
    }

    /**
     * Creates a task manager with pre-existing tasks.
     * This constructor is used when loading tasks from a file.
     *
     * @param tasks The list of tasks to manage.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a task manager with an empty task list.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @return The task number of the task in the task list (1-indexed).
     */
    public int addTask(Task task) {
        tasks.add(task);
        // newly added task is always the last one
        return tasks.size();
    }

    /**
     * Adds a task to the task list at the specified index.
     * If the index out of bounds (larger), ignore index.
     *
     * @param index The index to add the task.
     * @param task The task to add.
     */
    public void addTask(int index, Task task) {
        if (index > tasks.size()) {
            tasks.add(task);
        }
        tasks.add(index, task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns an iterator for reading tasks.
     * NOTE: Have to be explicitly casted to modify attributes (intended).
     *
     * @return ReadableTask iterator.
     */
    public Iterator<ReadableTask> getTasksIterator() {
        return new TaskIterator();
    }

    /**
     * Marks the task with the specified task number as done.
     *
     * @param taskNumber The task number to mark as done.
     * @return The task string that was marked as done.
     */
    public String markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotFoundException(
                String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        if (task.markAsDone() == false) {
            throw new TaskNotFoundException(
                String.format("BLAHH!!! The task number %s is already marked.", taskNumber));
        }
        return task.toString();
    }

    /**
     * Marks the task with the specified task number as undone.
     *
     * @param taskNumber The task number to mark as undone.
     * @return The task string that was marked as undone.
     */
    public String markTaskAsUndone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotFoundException(
                String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        if (task.markAsUndone() == false) {
            throw new TaskNotFoundException(
                String.format("BLAHH!!! The task number %s is already not marked.", taskNumber));
        }
        return task.toString();
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNumber The task number to delete.
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotFoundException(
                String.format("BLAHH!!! The task number %s to delete does not exist.", taskNumber));
        }
        Task task = tasks.remove(taskNumber - 1);
        return task;
    }

    /**
     * Returns a list of tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that contain the specified keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
