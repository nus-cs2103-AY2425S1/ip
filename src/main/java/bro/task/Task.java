package bro.task;

// This class encapsulates a task
public class Task {
    private final String content;
    private boolean isCompleted;

    public Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    public Task(String content, boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public void markTask() {
        this.isCompleted = true;
    }

    public void unmarkTask() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return (this.isCompleted) ? "[X] " + this.content: "[] " + this.content;
    }
}
