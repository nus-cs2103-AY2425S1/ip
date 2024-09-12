package FRIDAY;

/**
 * Represents a task
 */
public class Task {
    private boolean isComplete;
    private String taskDescription;

    /**
     * constructor for Task object
     * @param description task description
     * @param type completion status of task
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
     * Stores the task in a specific String format
     * @return String of task details in a specific format
     */
    public String storageDisplay() {
        String type = isComplete ? "1" : "0";
        return " | " + type + " | " + this.taskDescription;
    }

    public boolean getType() {
        return this.isComplete;
    }

    public boolean containsKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }
}
