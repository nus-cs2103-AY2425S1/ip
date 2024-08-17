public class Task {
    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return getStatus() + description;
    }
}
