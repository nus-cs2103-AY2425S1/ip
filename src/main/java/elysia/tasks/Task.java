package elysia.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void updateStatus(boolean status) {
        isDone = status;
    }

    public String toFile() {
        return "|" + (isDone ? "1" : "0") + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public boolean containsString(String searchString) {
        return description.contains(searchString);
    }
}
