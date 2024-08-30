package task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a task list
 * with a list of tasks
 *
 * @author celeschai
 */
public class TaskList {
    private ArrayList<Task> todoList = new ArrayList<>();

    /**
     * Instantiates empty task list
     */
    public TaskList() {
    }

    /**
     * Instantiates new task list with
     * existing tasks
     *
     * @param tasks existing tasks to be added
     */
    public TaskList(Task... tasks) {
        this.todoList.addAll(Arrays.asList(tasks));
    }

    public void addTask(Task task) {
        this.todoList.add(task);
    }

    public void markTaskAsDone(int taskInd) {
        this.todoList.get(taskInd - 1).markAsDone();
    }

    public void markTaskAsIncomplete(int taskInd) {
        this.todoList.get(taskInd - 1).markAsIncomplete();
    }

    public Task getTask(int taskInd) {
        return this.todoList.get(taskInd - 1);
    }

    public int getTotalNumOfTasks() {
        return this.todoList.size();
    }

    public Task removeTask(int taskIndex) {
        return this.todoList.remove(taskIndex - 1);
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
}
