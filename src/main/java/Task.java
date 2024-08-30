public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.completed = completed;
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String toFileFormat() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
