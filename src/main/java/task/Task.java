package task;


public abstract class Task {
    private final String description;

    private final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract Task setAsDone();

    public abstract Task setAsUndone();

    public abstract Task setDescription(String description);

    public abstract String toFileRecord();

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return (isDone() ? "[X] " : "[] ") + description;
    }
}
