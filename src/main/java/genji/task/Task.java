package genji.task;

/**
 * A class that can mark tasks as done and undone
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor of task class
     * The default status of task is undone
     * @param name Task's name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Get the name of task
     * @return Name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Formats the task's status into strings used for saving to file
     * @return The String to be saved
     */
    public String toListString() {
        if (isDone) {
            return " | 1 | " + name;
        } else {
            return " | 0 | " + name;
        }
    }

    /**
     * Formats the task's status into strings used for display
     * @return The String to be displayed
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
