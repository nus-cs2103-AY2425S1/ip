package task;

import java.util.ArrayList;

/**
 * Represents a task list with a list of tasks.
 *
 * @author celeschai
 */
public class TaskList {
    private ArrayList<Task> todoList = new ArrayList<>();

    /**
     * Instantiates empty task list.
     */
    public TaskList() {
    }

    /**
     * Adds Task to TaskList.
     *
     * @param task Task object to be added to the ArrayLst.
     */
    public void addTask(Task task) {
        int beforeSize = this.todoList.size();
        this.todoList.add(task);
        assert(this.todoList.size() == beforeSize + 1);
    }

    /**
     * Marks task as completed.
     *
     * @param taskInd Index of task to be marked. 1-indexed.
     */
    public void markTaskAsDone(int taskInd) {
        this.todoList.get(taskInd - 1).markAsDone();
    }

    /**
     * Marks task as incomplete.
     *
     * @param taskInd Index of task to be marked. 1-indexed.
     */
    public void markTaskAsIncomplete(int taskInd) {
        this.todoList.get(taskInd - 1).markAsIncomplete();
    }

    /**
     * Gets task by task index.
     *
     * @param taskInd Index of task to be retrieved. 1-indexed.
     */
    public Task getTask(int taskInd) {
        return this.todoList.get(taskInd - 1);
    }

    /**
     * Gets total number of tasks in the ArrayList.
     *
     * @return Total number of tasks.
     */
    public int getTotalNumOfTasks() {
        return this.todoList.size();
    }

    /**
     * Removes task by task index.
     *
     * @param taskInd Index of task to be removed. 1-indexed.
     * @return Task object removed.
     */
    public Task removeTask(int taskInd) {
        int beforeSize = this.todoList.size();
        Task removedTask = this.todoList.remove(taskInd - 1);
        assert(this.todoList.size() == beforeSize - 1);

        return removedTask;
    }

    /**
     * Checks if task index provided by user is valid
     * A valid task id means the task exists
     *
     * @param taskInd index of task in the list
     * @return if task exists
     */
    public boolean isTaskExist(int taskInd) {
        return this.todoList.size() >= taskInd;
    }

    /**
     * Enumerates and separates each task in the
     * task list on different lines
     *
     * @return string representation of task list
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.todoList.size();
        for (int n = 1; n <= len; n++) {
            Task task = getTask(n);
            str.append(String.format("%d. %s\n", n, task));
        }
        return str.toString();
    }

    /**
     * Formats task list into a String to be saved in .txt file
     *
     * @return text representation of task list
     */
    public String parseTaskListToTxt() {
        StringBuilder str = new StringBuilder();
        this.todoList.forEach(item -> str.append(String.format("%s\n", item)));

        return str.toString();
    }

    /**
     * Allows search of tasks by substring of name
     *
     * @param name substring of name to be searched
     * @return task list with matching task names
     */
    public TaskList findTasksByName(String name) {
        TaskList filteredList = new TaskList();
        this.todoList.stream()
                .filter(task -> task.nameContains(name))
                .forEach(filteredList::addTask);
        return filteredList;
    }
}
