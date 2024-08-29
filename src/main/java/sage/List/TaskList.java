package sage.List;

import sage.Sage;
import sage.SageException;
import sage.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on the index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws SageException If the task index is invalid.
     */
    public Task deleteTask(int index) throws SageException {
        if (index < 0 || index >= tasks.size()) {
            throw new SageException("Invalid task index.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks or un-marks a task in the list based on the index.
     *
     * @param index  The index of the task to be marked or unmarked.
     * @param isDone A boolean indicating if the task is marked as done or not.
     * @throws SageException If the task index is invalid.
     */
    public void markTask(int index, boolean isDone) throws SageException {
        if (index < 0 || index >= tasks.size()) {
            throw new SageException("Invalid task index.");
        }
        StringBuilder confirmationMessage = new StringBuilder(isDone
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n");

        Task task = tasks.get(index);
        task.setDone(isDone);
        Sage.ui.showResponse(String.valueOf(confirmationMessage.append(task)));
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            Sage.ui.showEmptyList();
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
            Sage.ui.showResponse(result.toString());
        }
    }
}
