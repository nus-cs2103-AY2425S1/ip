package asura.data.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks
 */
public class TaskList {
    List<Task> taskList;

    /**
     * Creates a new empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a new list of tasks with the specified tasks
     * @param taskList The tasks specified
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the size of the task list.
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task in the task list at the specified index.
     * @param index The index of the task to be removed.
     */
    public Task removeAt(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Marks a task as done in the specified index.
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks a task as undone in the specified index.
     * @param index The index of the task to be marked as undone.
     */
    public void unMark(int index) {
        taskList.get(index).markAsNotDone();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(i + 1).append(".").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
