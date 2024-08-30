package nixy.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nixy.Storage;
import nixy.exceptions.TaskNotExistException;

/**
 * Class that manages tasks as a collection.
 * It provides basic functionality for adding, deleting, listing, and marking tasks as done.
 */
public class TaskList {
    private Storage storage;
    private List<Task> tasks;

    /**
     * Inner class that provides an iterator for tasks.
     */
    private class TaskIterator implements Iterator<Task> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < tasks.size();
        }

        @Override
        public Task next() {
            return tasks.get(index++);
        }
    }

    /**
     * Creates a task manager with tasks loaded from file.
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

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns an iterator for tasks.
     * NOTE: Could potentially be used to modify existing tasks.
     * @return An iterator for tasks.
     */
    public Iterator<Task> getTasksIterator() {
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
            throw new TaskNotExistException(
                String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
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
            throw new TaskNotExistException(
                String.format("BLAHH!!! The task number %s to mark as done does not exist.", taskNumber));
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        return task.toString();
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNumber The task number to delete.
     * @return The task string that was deleted.
     */
    public String deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > this.getTaskCount()) {
            throw new TaskNotExistException(
                String.format("BLAHH!!! The task number %s to delete does not exist.", taskNumber));
        }
        Task task = tasks.remove(taskNumber - 1);
        return task.toString();
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
