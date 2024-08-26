package gravitas.tasklist;

import gravitas.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    ArrayList<Task> tasklist;

    /**
     * Constructor for TaskList.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param index Index of the task to be removed.
     */
    public void removeTask(int index) {
        this.tasklist.remove(index);
    }

    /**
     * Gets a task from the tasklist.
     *
     * @param index Index of the task to be retrieved.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Prints the tasklist.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        Task currentTask;
        for (int i = 0; i < this.tasklist.size(); i++) {
            currentTask = this.tasklist.get(i);
            System.out.println((i + 1) + ". [" + currentTask.getEventType() +
                    "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
    }

    /**
     * Prints a specific task.
     *
     * @param task Task to be printed.
     */
    public void printTask(Task task) {
        int size = tasklist.size();
        System.out.println("  [" + task.getEventType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the tasks that contain the keyword.
     *
     * @param keyword Keyword to be searched.
     */
    public void printFindTask(String keyword) {
        Task currentTask;
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < this.tasklist.size(); i++) {
            currentTask = this.tasklist.get(i);
            if (currentTask.getDescription().contains(keyword)) {
                System.out.println(count + ". [" + currentTask.getEventType() +
                        "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                count++;
            }
        }
    }

    /**
     * Returns the size of the tasklist.
     *
     * @return Size of the tasklist.
     */
    public int size() {
        return this.tasklist.size();
    }
}
