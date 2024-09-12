package gray.task;

import java.io.Serializable;

/**
 * A generic task that has a description.
 */
public class Task implements Serializable {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        char icon = isDone ? 'X' : ' ';
        return String.format("[%c] %s", icon, description);
    }

    public String getDescription() {
        return description;
    }
}
