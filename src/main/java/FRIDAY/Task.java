package FRIDAY;

/**
 * Represents a task with a description and a completion status.
 * <p>
 * This class provides methods to manage the state of a task, including
 * marking it as complete or incomplete and displaying its information
 * in different formats.
 * </p>
 */
public class Task {
    private boolean isComplete;
    private String taskDescription;

    /**
     * Constructs a new task object with the specified description and type.
     * <p>
     * The task's completion status is determined by the type parameter.
     * If type is greater than 0, the task is considered complete;
     * otherwise, it is incomplete.
     * </p>
     *
     * @param description the description of the task
     * @param type the type of the task; if greater than 0, the task is complete
     */
    public Task(String description, int type) {
        this.isComplete = type > 0;
        this.taskDescription = description;
    }

    public String getDescription() {
        return this.taskDescription;
    }

    public void check() {
        this.isComplete = true;
    }

    public void uncheck() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format((isComplete ? "[X]" : "[ ]") + " " + this.taskDescription);
        return str;
    }

    /**
     * Returns a string representation of the task suitable for display.
     * <p>
     * The format is "[X]" if the task is complete, or "[ ]" if it is not,
     * followed by the task description.
     * </p>
     *
     * @return the string representation of the task
     */
    public String storageDisplay() {
        String type = isComplete ? "1" : "0";
        return " | " + type + " | " + this.taskDescription;
    }

    public boolean getType() {
        return this.isComplete;
    }
}
