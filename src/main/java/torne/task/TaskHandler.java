package torne.task;

import torne.ui.ChatOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the list of tasks, as well as all operations concerning tasks.
 * This class does not interface with input or output.
 */
public class TaskHandler {
    private final ArrayList<Task> taskList;

    public TaskHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskHandler() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task torne.task.Task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "task should not be null";
        taskList.add(task);
    }

    /**
     * Returns the `torne.task.Task` at the specified position in this list.
     *
     * @param index index of the desired task. index is in [0, n-1].
     * @return The task at the position.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < getTaskCount() : "index must be in valid range [0, n-1]";
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Removes the `torne.task.Task` at the specified position in this list.
     *
     * @param index index of the desired task. index is in [0, n-1].
     * @return Returns the removed task.
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < getTaskCount() : "index must be in valid range [0, n-1]";
        Task removed = taskList.get(index);
        taskList.remove(index);

        return removed;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Gets the list of tasks as a formatted string, with each task on a separate line.
     *
     * @return the list of tasks: 1. ___ \n 2. ___ \n ...
     */
    public String getTaskListString() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String itemized = String.format("%d. %s\n", i + 1, taskList.get(i));
            taskListString.append(itemized);
        }

        return taskListString.toString().trim();
    }

    /**
     * Searches for tasks using a keyword substring and returns the list of tasks. Case-insensitive.
     *
     * @param keyword Keyword used to search. Matches if the name of a task contains the keyword.
     * @return The list of matching tasks
     */
    public List<Task> findTasksWithKeyword(String keyword) {
        assert keyword != null : "keyword should not be null";
        return taskList
                .stream()
                .filter((task) -> task.name.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Checks an index for validity against this taskHandler.
     * Also takes in the output to handle the error message (temp).
     * <br>
     * Used for all task-related commands that need use indexing to get the right task.
     *
     * @param index  Index to be checked. 0-indexed.
     * @param output {@link ChatOutput} instance.
     * @return `True` if index is valid, `False` if index is not.
     */
    public boolean indexChecker(int index, ChatOutput output) {
        if (index == -1) {
            output.error("Go away you nerd. We are a 1-indexed household.");
            return false;
        } else if (index < -1 || index >= getTaskCount()) {
            // TODO I'm guessing task handler should be the one raising this exception!
            output.error("Invalid task index. Out of range.");
            return false;
        }

        return true;
    }
}
