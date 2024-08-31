package Mario;

import Mario.Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;

    }

    /**
     * Adds a new task to the task list.
     * @param newTask
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Gets the task at index n.
     * @param n Index of the task (starting at 1).
     * @return
     */
    public Task getTask(int n) {
        return this.taskList.get(n - 1);
    }

    /**
     * Marks task at index n as complete.
     * @param n Index of the task (starting at 1).
     */
    public void markCompleted(int n) {
        Task task = this.taskList.get(n - 1);
        task.markCompleted();
    }

    /**
     * Unmarks task at index n as complete.
     * @param n Index of the task (starting at 1).
     */
    public void markUncompleted(int n) {
        Task task = this.taskList.get(n - 1);
        task.markUncompleted();
    }

    /**
     * Returns number of tasks in the task list.
     *
     */
    public int length() {
        return this.taskList.size();
    }

    /**
     * Removes task at index n.
     * @param n Index of the task (starting at 1).
     */
    public void removeTask(int n) {
        this.taskList.remove(n - 1);
    }

    /**
     * Returns all tasks in the TaskList as an ArrayList.
     *
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }


}
