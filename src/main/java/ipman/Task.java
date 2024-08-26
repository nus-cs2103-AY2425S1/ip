package ipman;

/**
 * Task class
 * @author miloaisdino
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     * @param description Name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task to not done
     * @return The new string representation of the task
     */
    public String unmarkStatus() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Sets the task to done
     * @return The new string representation of the task
     */
    public String markStatus() {
        this.isDone = true;
        return this.toString();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
