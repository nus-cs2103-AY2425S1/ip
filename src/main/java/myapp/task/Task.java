package myapp.task;

/**
 * The Task class represents a general task with a description and a completion status.
 * This is an abstract class that serves as a base for more specific types of tasks, such as ToDo, Deadline and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param s the description of the task.
     */
    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task object, including its completion status and description.
     *
     * @return a string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string formatted for saving to a file.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return a string representation of the Task object formatted for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns the status icon of the task, indicating whether the task is done or not.
     *
     * @return "X" if the task is done, otherwise a space (" ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }


    /**
     * Returns a description of the task
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}
