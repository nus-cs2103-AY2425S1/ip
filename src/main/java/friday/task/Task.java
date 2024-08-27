package friday.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
