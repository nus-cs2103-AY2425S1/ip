package sigma.task;

/**
 * Class for a Task
 *
 * @author Qiao Yi
 */
public class Task {
    private String name;
    private boolean status;

    /**
     * Constructor for a Task
     * @param name The name of the task
     * @param status The status of the task i.e. completed or not
     */
    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Sets the status of the task to the given boolean
     * @param status The status of the task to be set
     */
    public void setStatus(boolean status) { // setter
        this.status = status;
    }

    /**
     * Returns the name of the Task object
     * @return The name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Generates a string representation of a task
     * @return The task in string format
     */
    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.name;
    }
}
