package models;

public abstract class Task {
    protected boolean isDone = false;
    protected final String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public abstract String serialize();

    public abstract char getTaskType();

    @Override
    public String toString() {
        return String.format(
            "[%c][%s] %s",
            getTaskType(),
            this.isDone ? "X" : " ",
            this.name
        );
    }
}
