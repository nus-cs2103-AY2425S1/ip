package Tasks;

public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    protected Task(String description) {
        DESCRIPTION = description;
        this.isDone = false;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    };

    abstract public String getTaskType();

    abstract public String getExtraInfo();
}
