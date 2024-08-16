package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType; // Add the task type field

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType; // Initialize the task type
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        // Display the task type using the first character of the enum value
        return "[" + taskType.name().charAt(0) + "]" + getStatusIcon() + " " + description;
    }
}