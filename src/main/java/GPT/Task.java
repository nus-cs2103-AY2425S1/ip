package GPT;
abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public TaskType getType() {
        return type;
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s",
                this.getClass().getSimpleName().charAt(0),
                isDone ? 1 : 0,
                description);
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "][" + getStatusIcon() + "] " + description;
    }
}
