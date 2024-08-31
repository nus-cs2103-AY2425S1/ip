
public abstract class Task {
    private String description;
    private boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void complete() {
        this.isDone = true;
    }

    public void notComplete() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toStorageString();


    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + getStatusIcon() + " " + description;
    }
}
