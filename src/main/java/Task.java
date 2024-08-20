/**
 * Represents a task with a description and a completion status.
 * The task can be marked as done or not done.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The completion status of the task. True if the task is done, false otherwise. is false by default.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, a space otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Unmarks the task as not done and prints a confirmation message.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");

    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}