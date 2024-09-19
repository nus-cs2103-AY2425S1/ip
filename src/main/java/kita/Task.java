package kita;

/**
 * Overall parent Task class - Deadline, Event and ToDo inherits from this class
 */
public class Task {
    private boolean isCompleted;
    private final String name;

    /**
     * Initialises a Task, should not be called directly by developers
     */
    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public String getName() {
        return this.name;
    }

    public String type() {
        return " ";
    }

    @Override
    public String toString() {
        return "[" + (this.getCompleted() ? "X" : " ") + "] " + this.name;
    }
}
