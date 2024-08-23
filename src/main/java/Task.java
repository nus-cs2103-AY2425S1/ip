/**
 * Task is a class that encapsulates the characteristics of a Task.
 * It contains the field, description and isDone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
