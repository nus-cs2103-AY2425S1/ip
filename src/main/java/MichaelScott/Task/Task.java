package MichaelScott.Task;

/**
 * Represents a task in the program.
 * This is an abstract class that provides common properties and methods
 * for the children classes
 * Classes that inherit: Todo, DeadLine, Event
 */
public abstract class Task {
    protected Boolean isDone;
    protected String desc;

    /**
     * Constructs a Task with the specified description.
     *
     * @param Desc The description of the task.
     */
    public Task(String Desc) {
        this.isDone = false ;
        this.desc = Desc;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, otherwise false.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Marks the task as completed.
     *
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     *
     * */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The String representation of the task.
     */
    public abstract String toString();

    /**
     * Returns the description of the task in the file.
     *
     * @return The String representation of the task in the file.
     */
    public abstract String toFile();
}
