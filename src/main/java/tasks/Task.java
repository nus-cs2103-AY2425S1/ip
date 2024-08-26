package tasks;
import java.io.Serializable;

public class Task implements Serializable {
    private final String description;
    private boolean isDone;
    private static final long serialVersionUID = 1L;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}