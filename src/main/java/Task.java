// This class encapsulates a task
public class Task {
    private final String content;
    private boolean isCompleted;

    public Task(String content) {
        this.content = content;
        this.isCompleted = false;
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
