package beeboo.task;

/**
 * The Tasks class represents a generic task with a description and completion status.
 * It serves as the base class for specific types of tasks such as deadlines, events, and to-dos.
 */
public abstract class Tasks {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a Tasks instance with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representation of the task, including the completion icon and description.
     *
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        return completionIcon() + " " + description;
    }

    /**
     * Returns the completion icon based on the task's completion status.
     * The icon is "[X]" if the task is done, and "[ ]" if it is not done.
     *
     * @return the completion icon as a String
     */
    protected String completionIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the type icon for the task.Implemented by subclass to show their own
     * typeIcon
     *
     * @return the type icon as a String
     */
    public abstract String typeIcon();

    /**
     * Returns a String representation of the task in a format suitable for saving to storage.
     * Implemented by subclasses to have their own save format
     *
     * @return a String representing the saved format of the task
     */
    public abstract String saveFormat();

    public String getDescription() {
        return description;
    }

    public abstract void updateTime(String time);
}
