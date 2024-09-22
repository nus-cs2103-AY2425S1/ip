package duck.tasks;

import duck.exceptions.BeforeEarliestTimeException;

/**
 * Class representing tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the UI representation of whether task is done
     *
     * @return "X" if task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void markAsDone() throws BeforeEarliestTimeException {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
