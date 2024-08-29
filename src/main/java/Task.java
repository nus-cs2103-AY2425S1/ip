/**
 * Represents a generic task with a description and a completion status.
 * Provides methods to mark the task as done or not done, and to get the task's status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, otherwise a space character.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status icon of the task.
     * "1" if the task is done, otherwise a space character.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }
    /**
     * Marks the task as done. If the task is already done, a message indicating
     * that it is already completed is displayed.
     */
    public void markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:" );
            System.out.println("[" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            System.out.println("This tasked has been already been completed!\n");
        }

    }

    /**
     * Marks the task as not done. If the task is already not done, a message indicating
     * that it is not marked yet is displayed.
     */
    public void unMark() {
        if (this.isDone) {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            System.out.println("This tasked has not been marked yet!!\n");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " +  this.description;
    }

    /**
     * Abstract method to get a string representation of the task for storing in a file.
     * Concrete subclasses should provide their own implementation.
     *
     * @return A string representation of the task for storage purposes.
     */
    public abstract String toStore();
}
