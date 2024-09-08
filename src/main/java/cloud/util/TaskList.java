package cloud.util;

import java.util.ArrayList;

import cloud.task.Task;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the end of the list
     *
     * @param task cloud.task.Task object to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove a task from the list
     *
     * @param taskId index of the task
     * @return the deleted Task
     */
    public Task delete(int taskId) {
        Task deletedTask = this.tasks.remove(taskId - 1);
        return deletedTask;
    }

    /**
     * Mark a task as done
     *
     * @param taskId Index of the task to be marked
     */
    public void mark(int taskId) {
        tasks.get(taskId - 1).markDone();
    }

    /**
     * Mark a task as not done
     *
     * @param taskId Index of the task to be unmarked
     */
    public void unmark(int taskId) {
        tasks.get(taskId - 1).markNotDone();
    }

    /**
     * Get the current status of a task
     *
     * @param taskId index of the task
     * @return a string representation of the task
     */
    public String getTaskStatus(int taskId) {
        return tasks.get(taskId - 1).toString();
    }

    /**
     * Get the status of the last added task
     *
     * @return String representation of the task
     */
    public String getLatestTask() {
        if (tasks.isEmpty()) {
            return "";
        }
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Get the total number of tasks
     *
     * @return Count of all marked and unmarked tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Get a task from the list
     *
     * @param index index of the task
     * @return the requested task object
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public TaskList find(String keyword) {
        TaskList tasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int nTasks = getTaskCount();
        for (int index = 0; index < nTasks; index++) {
            sb.append(String.format("%d: %s\n", index + 1, tasks.get(index)));
        }
        return sb.toString();
    }
}
