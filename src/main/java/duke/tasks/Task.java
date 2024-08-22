package duke.tasks;

/**
 * Represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task
     * @param taskType The type of the task
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done and prints a message.
     */
    public void markAsDone() {
        if (this.isDone) System.out.println("Task already marked as done:\n" + this);
        else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + this);
        }
    }

    /**
     * Marks the task as done without printing a message.
     */
    public void markAsDoneNonVerbose() {
        if (!this.isDone) this.isDone = true;
    }

    /**
     * Marks the task as not done and prints a message.
     */
    public void markAsNotDone() {
        if (!this.isDone) System.out.println("Task already marked as not done:\n" + this);
        else {
            this.isDone = false;
            System.out.println("Ok, I've marked this task as not done yet:\n" + this);
        }
    }

    /**
     * Returns a string representation of the task for saving.
     *
     * @return A string representation of the task
     */
    public String saveFormat() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }
}
