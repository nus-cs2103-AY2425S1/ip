package struggling.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        char label = this.isDone ? 'X' : ' ';
        return String.format("[%s] %s", label, this.description);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getState() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }
}
