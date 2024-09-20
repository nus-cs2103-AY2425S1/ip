package tasks;

import java.util.Objects;

/**
 * Abstract class for all task type.
 */
public abstract class Task {
    private boolean isComplete;
    private final String description;

    /**
     * Returns an instance of a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }

    /**
     * Returns an instance of a Task.
     *
     * @param description Description of the task.
     * @param isComplete Completion state of Task object.
     */
    public Task(String description, boolean isComplete) {
        this.isComplete = isComplete;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the objet as complete.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Unmarks the object as incomplete.
     */
    public void unMark() {
        this.isComplete = false;
    }

    /**
     * Returns a string representation of completion status.
     *
     * @return X if completed and an empty space otherwise.
     */
    public String getCompletedStringRepresentation() {
        return !isComplete ? " " : "X";
    }

    /**
     * Returns an integer representation of completion status.
     *
     * @return 1 if completed and 0 if incomplete.
     */
    public int isCompleteAsInteger() {
        return isComplete ? 1 : 0;
    }

    /**
     * Returns string representation of Task instance.
     *
     * @return String representation of Task instance.
     */
    public abstract String getSaveFormat();

    /**
     * Returns a String representation of the object.
     *
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getCompletedStringRepresentation(), this.description);
    }

    /**
     * Returns true if this object and the object input is equal else false.
     *
     * @return True if this object and the object input is equal else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isComplete == task.isComplete && Objects.equals(description, task.description);
    }

    /**
     * Returns hashcode.
     *
     * @return Hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(isComplete, description);
    }
}
