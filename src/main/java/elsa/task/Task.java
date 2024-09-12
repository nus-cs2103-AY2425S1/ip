package elsa.task;

/**
 * Represents a task.
 * @author Aaron
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark a done task with 'X'
    }

    public String getDescription() {
        return description;
    }

    public void done() {
        this.isDone = true;
    }

    public void notDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
