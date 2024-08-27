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

    public void setCompleted(boolean completed) {
        this.status = completed ? TaskStatus.DONE : TaskStatus.NOT_DONE;
    }

    public boolean isCompleted() {
        return this.status == TaskStatus.DONE;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public abstract String toString();
}