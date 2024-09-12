package task;

/**
 * Represents a task
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor the task
     * 
     * @param description The description for the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String pref = isDone ? "[X] " : "[ ] ";
        return pref + description;
    }
}
