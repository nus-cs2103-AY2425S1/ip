package shrimp.task;

public abstract class Task {
    private final String description;
    private final Boolean isDone;

    Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getIsDone() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getIsDone() + "] " + description;
    }

    public abstract Task markAsDone();

    public abstract Task markAsNotDone();

    public abstract String getType();
}
