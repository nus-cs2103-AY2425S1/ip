/**
 * Task class for task representation.
 */
public class Task {
    private String taskName;
    private Boolean completed;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Reverts task to incomplete
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Returns status of task; true if complete, false otherwise.
     * @return String representation of complete or incomplete
     */
    public String getStatus() {
        return this.completed ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatus(), this.taskName);
    }
}
