package tasks;

import java.util.Objects;

public abstract class Task {
    private boolean isComplete;
    private final String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }

    public Task(String description, boolean isComplete) {
        this.isComplete = isComplete;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean getComplete() {
        return isComplete;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unMark() {
        this.isComplete = false;
    }

    public String getCompletedStringRepresentation() {
        return !isComplete ? " " : "X";
    }

    public int isCompleteAsInteger() {
        return isComplete ? 1 : 0;
    }

    public boolean booleanComplete(int integer) {
        return integer == 1;
    }

    public abstract String getSaveFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getCompletedStringRepresentation(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isComplete == task.isComplete && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isComplete, description);
    }
}
