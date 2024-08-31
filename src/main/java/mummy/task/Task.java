package mummy.task;


public abstract class Task {
    private final String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String toFileRecord();

    public void setAsDone() {
        this.isDone = true;
    };

    public void setAsUndone() {
        this.isDone = false;
    };

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
