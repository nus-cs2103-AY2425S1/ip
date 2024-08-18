public abstract class Task {
    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X and undone task with space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }
}
