import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    abstract String getSymbol();
    abstract String getTaskLine();
    abstract boolean isRelevant(LocalDate date);

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

//    public Task(String description) {
//        this(description, false);
//    }

    String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    void markAsDone() {
        this.isDone = true;
    }

    void markAsUndone() {
        this.isDone = false;
    }

    int isDoneBinary() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns a string representation of the task.
     * Includes the status icon and the task description.
     *
     * @return Task as a formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
