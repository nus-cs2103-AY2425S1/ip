package lutchat;

public abstract class Task {
    private boolean done;
    private String description;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String toFileFormat() {
        return getTaskType() + " | "
                + (done ? "1" : "0") + " | "
                + description
                + additionalDescDetailsToFileFormat();
    }

    public abstract String getTaskType();

    public abstract String additionalDescDetailsToFileFormat();

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + description;
    }
}
