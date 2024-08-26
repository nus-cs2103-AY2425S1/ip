package calebyyy;

import java.util.ArrayList;

import calebyyy.Tasks.*;

/**
 * Responsible for storing and manipulating the list of tasks.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * The Ui object responsible for user interaction.
     */
    private Ui ui;
    
    /**
     * Constructor for TaskList.
     * 
     * @param ui The Ui object responsible for user interaction.
     */
    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Prints list of tasks.
     */
    public void listTasks() {
        ui.listTasksMessage(this);
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done.
     * 
     * @param taskNumber The number of the task to be marked as done.
     */
    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Unmarks a task as done.
     * 
     * @param taskNumber The number of the task to be unmarked as done.
     */
    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    /**
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes a task from the list.
     * 
     * @param taskNumber The number of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        ui.deleteTaskMessage(task, tasks.size());
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Finds tasks with a keyword.
     * 
     * @param keyword The keyword to search for.
     */
    public void findKeyword(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.listTasksWithKeyword(foundTasks);
    }
    
}
