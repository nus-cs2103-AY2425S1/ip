public abstract class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone = false;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
    }

    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[%s][%s] %s".formatted(type, getStatusIcon(), description);
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }
}
