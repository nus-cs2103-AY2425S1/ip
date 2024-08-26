package tasks;

import java.util.Objects;

public abstract class Task {
    private boolean completed;
    private final String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return completed == task.completed && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, description);
    }
}
