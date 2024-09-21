package chobo;

import java.util.Objects;

/**
 * The type Task.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param done the done
     */
    public Task(String name, boolean done) {
        this.isDone = done;
        this.name = name;
    }

    /**
     * Returns type of task.
     *
     * @return Type of task
     */
    public abstract String getType();

    /**
     * Returns String representation formatted for file.
     *
     * @return String representation of task
     */
    public abstract String toFileString();

    /**
     * Mark.
     */
    public void mark() {
        this.isDone = true;
        assert isDone == true: "Task should be marked as done";
    }

    /**
     * Returns Whether task is done.
     *
     * @return Boolean on whether task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks tasks as undone.
     */
    public void unmark() {
        this.isDone = false;
        assert isDone == false : "Task should be marked as undone";
    }

    /**
     * Returns task's name.
     *
     * @return the name
     */
    public String getTaskName() {
        return this.name;
    }

    @Override
    public String toString() {
        String checked = this.isDone ? "[X] " : "[ ] ";
        return (checked + this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return this.name.equals(task.name) &&
                this.getType().equals(task.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
