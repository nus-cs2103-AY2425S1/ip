package ai.task;

public class Task {
    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public String stringData() {
        int isDoneNum = isDone ? 1 : 0;
        return isDoneNum + " | " + description;
    }
}
