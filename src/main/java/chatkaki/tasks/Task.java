package chatkaki.tasks;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done and prints the task status.
     */
    public void markAsDone() {
        if (this.isDone) {
            System.out.println("Task was already done!");
            return;
        }
        this.isDone = true;
        printTaskStatus("Nice! I've marked this task as done:");
    }

    /**
     * Marks the task as not done and prints the task status.
     */
    public void markAsUndone() {
        if (!this.isDone) {
            System.out.println("Task was already undone!");
            return;
        }
        this.isDone = false;
        printTaskStatus("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints the task status with the specified message.
     *
     * @param message The message to print.
     */
    private void printTaskStatus(String message) {
        System.out.println("\n____________________________________________________________");
        System.out.println(" " + message);
        System.out.println(" " + this);
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Formats the task to be saved in the file.
     *
     * @return The formatted task.
     */
    public String fileFormat() {
        return (this.isDone ? "true" : "false") + ","+ this.getDescription();
    }

    /**
     * Formats the task to be displayed to the user.
     *
     * @return The formatted task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.getDescription();
    }
}
