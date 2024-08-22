public class Task {
    private final String description;
    private boolean isDone;

    // Protected as we still want Brock (in same package) to access
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getDescription() {
        return this.description;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : "";
    }
}
