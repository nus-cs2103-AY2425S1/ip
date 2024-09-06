package fridayproject;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> tasks;

    /*
     * Constructor for TaskList with existing tasks.
     * @param tasks
     */
    public TaskList(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }
    /*
     * Constructor for TaskList with no existing tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Tasks task) {
        tasks.add(task);
    }   

    /*
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Tasks deleteTask(int index) {
        return tasks.remove(index);
    }

    /*
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /*
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    /*
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    /*
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /*
     * Gets a task at a specific index.
     * @param index The index of the task to be retrieved.
     *  @return The task at the specified index.
     */
    public Tasks getTask(int index) {
        return tasks.get(index);
    }
}
