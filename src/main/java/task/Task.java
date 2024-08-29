package task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;

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
