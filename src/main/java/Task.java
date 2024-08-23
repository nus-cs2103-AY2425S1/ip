public abstract class Task {
    private final String description;
    private final Boolean hasCompleted;

    Task(String description) {
        this.description = description;
        this.hasCompleted = false;
    }

    Task(String description, Boolean isDone) {
        this.description = description;
        this.hasCompleted = isDone;
    }

    private String getHasCompleted() {
        return (hasCompleted ? "X" : " "); // mark done task with X
    }

    protected String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getHasCompleted() + "] " + description;
    }

    public abstract Task markAsDone();
    public abstract Task markAsNotDone();
    public abstract String getType();
}
