package nathanbot.tasks;

import java.util.ArrayList;

/**
 * Manages a list of tasks and handles operations such as adding, marking as done, and deleting tasks.
 */
public class TaskList {
    protected final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markAsUndone(int index) {
        taskList.get(index).unmarkAsDone();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int listLength() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "You have no tasks in the list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}