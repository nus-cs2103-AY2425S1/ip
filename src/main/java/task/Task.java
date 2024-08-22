package task;

/**
 * A task stored by BotManager
 *
 * @author dwsc37
 */
public class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    protected void markTask() {
        isDone = true;
    }

    protected void unmarkTask() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
