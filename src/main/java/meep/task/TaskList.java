package meep.task;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides methods to add, delete, and manipulate tasks, as well as retrieve information about the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} that is a copy of the specified {@code TaskList}.
     *
     * @param taskList The {@code TaskList} to copy.
     */
    public TaskList(TaskList taskList) {
        this.tasks = new ArrayList<>(taskList.tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param item The task to be added.
     */
    public void addItem(Task item) {
        this.tasks.add(item);
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteItem(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        assert index >= 0 : "Index should be non-negative";
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markAsUndone(int index) {
        assert index >= 0 : "Index should be non-negative";
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Returns a string representation of the task at the specified index.
     * The returned string includes a newline character at the end.
     *
     * @param index The index of the task to be retrieved.
     * @return The string representation of the task at the specified index.
     */
    public String getTask(int index) {
        assert index >= 0 : "Index should be non-negative";
        return this.tasks.get(index).toString() + "\n";
    }

    /**
     * Returns a string representation of the last task in the list.
     *
     * @return The string representation of the last task.
     */
    public String getLastTask() {
        return this.tasks.get(this.tasks.size() - 1).toString();
    }

    /**
     * Returns a string representation of all tasks in the task list.
     * Each task is preceded by its index in the list.
     *
     * @return The string representation of the entire task list.
     */
    public String getList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            list.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return list.toString();
    }

    /**
     * Returns a string representation of all tasks in the task list in a format suitable for saving to a file.
     * Each task's string representation is followed by a newline character.
     *
     * @return The save format string of the entire task list.
     */
    public String getSaveFormatList() {
        StringBuilder list = new StringBuilder();
        for (Task task : this.tasks) {
            list.append(task.getSaveFormat()).append("\n");
        }
        return list.toString();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword.
     * <p>
     * This method iterates through all tasks in the list and checks if the task description
     * (converted to lowercase) contains the given keyword (also converted to lowercase).
     * Tasks that match the criteria are added to a new {@code TaskList} object.
     * </p>
     *
     * @param keyword The keyword to search for in the task descriptions. It is case-insensitive.
     * @return A formatted string of tasks that contain the keyword. If no tasks match, an empty string is returned.
     */
    public String findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addItem(task);
            }
        }
        return foundTasks.getList();
    }
}
