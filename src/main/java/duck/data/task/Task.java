package duck.data.task;

/**
 * Represents a task with a description and a completion status.
 * The Task class provides methods to mark the task as done or not done and
 * to retrieve the task's status and description in different formats.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified completion status and description.
     *
     * @param isDone Indicates whether the task is completed.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task's completion status.
     * The status is represented as "X" for completed tasks and a space for not done tasks.
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
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                this.toString());
        System.out.println();
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public void markAsIncomplete() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" +
                this.toString());
        System.out.println();
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * The format is "1/0 | description" where "1" indicates completed and "0" indicates not done.
     *
     * @return A string representation of the task in file format.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task in a user-friendly format.
     * The format is "[status icon] description" where status icon is "X" or a space.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
