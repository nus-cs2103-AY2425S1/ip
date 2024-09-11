package bean.task;

/**
 * Represents an abstract task with a name, completion status, and icon.
 * Subclasses must implement methods to get task details and convert the task to a saveable format.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String icon = " ";

    /**
     * Constructs a Task with the specified name and blank icon.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.icon = " ";
    }

    /**
     * Retrieves the details of the task.
     * Subclasses must implement this method to provide task-specific details.
     *
     * @return A string representing the details of the task.
     */
    public abstract String getDetails();

    /**
     * Converts the task to a format suitable for saving to a file.
     * Subclasses must implement this method to provide task-specific save format.
     *
     * @return A string representing the task in a saveable format.
     */
    public abstract String toSaveFormat();

    /**
     * Retrieves the icon representing the task's completion status.
     *
     * @return The icon for the task.
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the isDone of the task
     *
     * @return The isDone of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed and resets the icon.
     */
    public void completeTask() {
        this.isDone = true;
        this.icon = "X";
    }

    /**
     * Marks the task as not completed and resets the icon.
     */
    public void undoTask() {
        this.isDone = false;
        this.icon = " ";
    }

    /**
     * Returns a string representation of the task, including its completion status and name.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.icon + "] " + name;
    }


}
