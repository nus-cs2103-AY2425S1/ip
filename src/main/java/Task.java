public class Task {
    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    // Function to get task status icon
    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    // Function to get the task string
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    // Function to mark the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Function to unmark the task
    public void markAsNotDone() {
        this.isDone = false;
    }
}
