public abstract class Task {
    private final String taskName;
    private boolean isCompleted;
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    public String export() {
        return String.format("%s %s", this.isCompleted, this.taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isCompleted ? "X" : " ", this.taskName);
    }
}
