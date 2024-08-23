/**
 * Represents a Task with a description and completion status.
 *
 * This class allows for marking the task as done or not done,
 * and provides a textual representation of the task.
 *
 * @author Judy Park
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return description;
    }

    public String toStringDetails() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
