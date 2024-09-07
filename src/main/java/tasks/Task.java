package tasks;

import java.util.Objects;

/**
 * Abstract class for all task type.
 */
public abstract class Task {
    private boolean completed;
    private final String description;

    /**
     * Sets the description for all task type.
     *
     * @param description Is the description of the task.
     */
    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    /**
     * Sets the description and complete flag for all task type.
     *
     * @param description Is the description of the task.
     * @param completed Is the complete flag.
     */
    public Task(String description, boolean completed) {
        this.completed = completed;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unMark() {
        this.completed = false;
    }

    /**
     * To represents completion status.
     *
     * @return X if completed and an empty space otherwise.
     */
    public String completedStringRepresentation() {
        if (!completed) {
            return " ";
        } else {
            return "X";
        }
    }

    public int intComplete() {
        return completed ? 1 : 0;
    }

    public boolean booleanComplete(int integer) {
        return integer == 1;
    }

    public abstract String getSaveFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", completedStringRepresentation(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return completed == task.completed && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, description);
    }
}
