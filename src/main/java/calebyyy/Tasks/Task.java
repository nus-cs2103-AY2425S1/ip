package calebyyy.tasks;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String toSaveFormat() {
        return " | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(this.toString());

    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
