package FRIDAY;

/**
 * This class represents a Task that has been entered by a user.
 *
 * <p>
 *     This class represents a task entered by the user, storing all relevant information associated with the task.
 * </p>
 */
public class Task {
    private boolean isComplete;
    private String taskDescription;

    /**
     * Constructor for Task object.
     *
     * @param description task description.
     * @param type completion status of task.
     */
    public Task(String description, int type) {
        this.isComplete = type > 0;
        this.taskDescription = description;
    }

    public String getTaskDescription() {
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
     * This method represents the task details in a specific format for writing to storage.
     *
     * @return String of task details in a specific format.
     */
    public String storageDisplay() {
        String type = isComplete ? "1" : "0";
        return " | " + type + " | " + this.taskDescription;
    }

    /**
     * Returns the completion status of this task.
     * @return Boolean value representing whether the task is complete.
     */
    public boolean getType() {
        return this.isComplete;
    }

    /**
     * Returns True if the description of the task contains the keyword as input by the user.
     * @param keyword String representing the keyword input by user.
     * @return Boolean value representing whether the keyword is present in the task description.
     */
    public boolean containsKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }
}
