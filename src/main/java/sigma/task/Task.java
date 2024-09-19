package sigma.task;

/**
 * Class for a Task
 *
 * @author Qiao Yi
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for a Task
     * @param name The name of the task
     * @param isDone The status of the task i.e. completed or not
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the status of the task to the given boolean
     * @param done The status of the task to be set
     */
    public void setDone(boolean done) { // setter
        this.isDone = done;
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
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
