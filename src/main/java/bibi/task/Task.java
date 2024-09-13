package bibi.task;

/**
 * Represents a Task using a description and completion status.
 */
public class Task {
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public String getDescription() {
        return task;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", task);
    }
}
