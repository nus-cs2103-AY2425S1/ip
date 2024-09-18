package utility;

import java.io.IOException;
import java.util.ArrayList;

import tasks.Task;

/** Wrapper class for tasks recorded by Luke. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Returns an instance of TaskList object.
     *
     * @param storage Where tasks are stored.
     * @throws IOException When retrieving tasks fails.
     */
    public TaskList(Storage storage) throws IOException {
        this.tasks = storage.load();
    }

    /** Returns the size of tasks. */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param t Is the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Find tasks matching the given description.
     *
     * @param description Is description of a task.
     * @return A list of task matching the description.
     */
    public ArrayList<Task> findTasks(String description) {
        ArrayList<Task> findings = new ArrayList<>();
        for (Task t: tasks) {
            if (!t.getDescription().contains(description)) {
                continue;
            }
            findings.add(t);
        }
        return findings;
    }

    /**
     * Marks a Task as complete.
     *
     * @param index Is the index of the task.
     * @return The instance of the task that is marked.
     */
    public Task markTask(int index) {
        if (index >= tasks.size() || index < 0) {
            return null;
        }
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
        if (index >= tasks.size() || index < 0) {
            return null;
        }
        Task t = tasks.get(index);
        t.unMark();
        return t;
    }

    /**
     * Removes a Task from the list of existing tasks.
     * @param index Is the index of the Task.
     * @return A Task of corresponding index.
     */
    public Task deleteTask(int index) {
        if (index >= tasks.size() || index < 0) {
            return null;
        }
        Task t = tasks.get(index);
        tasks.remove(index);
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
