package tudee.task;

/**
 * Represents a Task. A Task has a description and a status indicating
 * whether it is done or not. This class is abstract and is meant to be
 * extended by specific types of task.
 */
public abstract class Task {
    protected String taskString;
    protected boolean done;

    /**
     * Constructs a new Task object with the specified task description.
     * The task is initially marked as incomplete.
     *
     * @param taskString The task description.
     */
    public Task(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return "[X]" if the task is done, otherwise "[ ]".
     */
    public String getDone() {
        return (this.done ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * Converts the task into a formatted string for saving to a file.
     * This method must be implemented by subclasses as it is an abstract method.
     *
     * @return A formatted string for the task.
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task, including
     * its type and completion status.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return this.getDone() + " " + this.taskString;
    }
}
