abstract class Task {
    private final String description;
    private boolean isDone;

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
        return this.isDone ? "X" : " ";
    };

    abstract protected String getTaskType();

    abstract protected String getExtraInfo();
}
