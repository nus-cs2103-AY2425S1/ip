package wolfie.task;

import java.util.Objects;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final TaskType taskType; // wolfie.task.TaskType enum

    /**
     * Constructor for a task.
     *
     * @param description Description of the task.
     * @param taskType Type of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X and undone task with space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String toFileFormat(); // abstract method to be implemented by subclasses

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
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
        return isDone == task.isDone && Objects.equals(description, task.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
