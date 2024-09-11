package lunabot.task;

import java.util.ArrayList;

import lunabot.exception.LunaBotException;

/**
 * Represents a list of tasks, providing methods to add, delete, retrieve, and manage tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Checks whether the TaskList is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed.
     * @throws LunaBotException If the index is invalid (less than 0 or greater than the size of the list).
     */
    public Task deleteTask(int index) throws LunaBotException {
        if (index < 0 || index >= taskList.size()) {
            throw new LunaBotException(" Invalid task number.");
        }
        return taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the given index.
     * @throws LunaBotException If the index is invalid (less than 0 or greater than the size of the list).
     */
    public Task get(int index) throws LunaBotException {
        if (index < 0 || index >= taskList.size()) {
            throw new LunaBotException(" Invalid task number.");
        }
        return taskList.get(index);
    }

    /**
     * Finds and returns a matching list of tasks from the current taskList.
     *
     * @param keyword The keyword to search for in the taskList entered by the user.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
