public abstract class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    public void markAsNotDone() {
        this.status = TaskStatus.NOT_DONE;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type.getSymbol(), status.getSymbol(), description);
    }
}
