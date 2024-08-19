/**
 * This class implements a Task object.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Task {
    private String task;
    private boolean complete;

    /**
     * Constructor for a task. Initializes completion of task as false.
     *
     * @param task String description of task.
     */
    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    /**
     * Method to update the task as complete.
     */
    public void complete() {
        this.complete = true;
    }

    /**
     * Method to return the String description of task.
     *
     * @return String description of task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Method to check if a task is complete.
     *
     * @return Boolean indicating if the task is complete.
     */
    public Boolean isComplete() {
        return this.complete;
    }
}
