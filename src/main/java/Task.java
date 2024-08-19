public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to get the status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    // Method to mark the task as done
    public void markAsDone() {
        isDone = true;
    }

    // Method to mark the task as not done
    public void markAsNotDone() {
        isDone = false;
    }

    // Override toString() method to display the task details
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
