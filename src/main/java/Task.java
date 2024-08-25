public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
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

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
