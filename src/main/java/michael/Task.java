package michael;

public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void doTask() {
        this.isDone = true;
    }

    public void undoTask() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}