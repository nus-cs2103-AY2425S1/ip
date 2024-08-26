package utility;

import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/** Wrapper class for tasks recorded by Luke. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Returns the size of tasks. */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Returns an instance of TaskList object.
     * @param storage Where tasks are stored.
     * @throws IOException When retrieving tasks fails.
     */
    public TaskList(Storage storage) throws IOException {
        this.tasks = storage.load();
    }

    /**
     * Adds a task to the list of existing tasks.
     * @param t Is the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a Task from the list of existing tasks.
     * @param index Is the index of the Task.
     * @return A Task of corresponding index.
     */
    public Task deleteTask(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    /**
     * Prints out all the tasks with their corresponding index.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * Marks a Task as complete.
     * @param index Is the index of the task.
     * @return The instance of the task that is marked.
     */
    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.mark();
        return t;
    }

    /**
     * Unmarks a Task as incomplete.
     * @param index Is the index of the task.
     * @return The instance of the task that is unmarked.
     */
    public Task unMarkTask(int index) {
        Task t = tasks.get(index);
        t.unMark();
        return t;
    }

    /**
     * Saves the tasks to local hard disk.
     * @param storage Where tasks are stored.
     * @throws IOException When storage fails.
     */
    public void save(Storage storage) throws IOException {
        storage.save(tasks);
    }
}
