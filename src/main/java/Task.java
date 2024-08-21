/**
 * Task object model
 */

public class Task {
    protected int index;
    protected String description;
    protected boolean isDone;

    public Task (int index, String description) {
        this.index = index;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
