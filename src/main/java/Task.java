abstract class Task {
    private final TaskType type;
    private final String description;
    private boolean isDone = false;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[%s][%s] %s".formatted(type, isDone ? "X" : " ", description);
    }
}
