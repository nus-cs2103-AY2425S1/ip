package gravitas.tasklist;

import java.util.ArrayList;

import gravitas.task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasklist;

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
     *
     * @return String representation of the tasklist.
     */
    public String printTaskList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        Task currentTask;
        for (int i = 0; i < this.tasklist.size(); i++) {
            currentTask = this.tasklist.get(i);
            String msg = "\n" + (i + 1) + ". [" + currentTask.getEventType()
                    + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
            message.append(msg);
        }
        return message.toString();
    }

    /**
     * Prints a specific task.
     *
     * @param task Task to be printed.
     * @return String representation of the task.
     */
    public String printTask(Task task) {
        int size = tasklist.size();
        String msg = "  [" + task.getEventType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
        String taskLeft = "Now you have " + size + " tasks in the list.";
        return (msg + "\n" + taskLeft);
    }

    /**
     * Prints the tasks that contain the keyword.
     *
     * @param keyword Keyword to be searched.
     * @return String representation of the tasks that contain the keyword.
     */
    public String printFindTask(String keyword) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        Task currentTask;
        int count = 1;
        for (int i = 0; i < this.tasklist.size(); i++) {
            currentTask = this.tasklist.get(i);
            if (currentTask.getDescription().contains(keyword)) {
                String msg = "\n" + count + ". [" + currentTask.getEventType()
                        + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
                message.append(msg);
                count++;
            }
        }
        return message.toString();
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
