package todo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a todolist
 * with a list of tasks
 *
 * @author celeschai
 */
public class TodoList {
    private ArrayList<Task> todoList = new ArrayList<>();

    /**
     * Instantiates empty todo list
     */
    public TodoList() {}

    /**
     * Instantiate new todo list with
     * existing tasks
     *
     * @param tasks existing tasks to be added
     */
    public TodoList(Task ...tasks) {
        this.todoList.addAll(Arrays.asList(tasks));
    }

    public void addTask(Task task) {
        this.todoList.add(task);
    }

    public void markTaskAsDone(int taskInd) {
        this.todoList.get(taskInd).markAsDone();
    }

    public void markTaskAsIncomplete(int taskInd) {
        this.todoList.get(taskInd).markAsIncomplete();
    }

    public Task getTask(int taskInd) {
        return this.todoList.get(taskInd);
    }

    public int getTotalNumOfTasks() {
        return this.todoList.size();
    }

    public Task removeTask(int taskIndex) {
        return this.todoList.remove(taskIndex);
    }

    /**
     * Checks if task index provided by user is valid
     * A valid task id means the task exists
     *
     * @param taskInd index of task in the list
     * @return if task exists
     */
    public boolean isTaskExist(int taskInd) {
        return this.todoList.size() > taskInd;
    }

    /**
     * Enumerates and separates each task in the
     * todo list on different lines
     *
     * @return string representation of todo list
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.todoList.size();
        for (int n = 0; n < len; n++) {
            Task task = this.todoList.get(n);
            str.append(String.format("%d. %s\n", n, task));
        }
        return str.toString();
    }
}
