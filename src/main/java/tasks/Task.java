package tasks;

/**
 * Abstract Task class to common attributes and methods between concrete tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isMarked;

    /**
     * Constructor for Deadline class.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Marks or unmarks task.
     *
     * @param isMarking Mark or unmark task.
     * @return Success of marking or unmarking task.
     */
    public boolean mark(boolean isMarking) {
        // Tasks.Task is already marked correctly
        if (this.isMarked == isMarking) {
            return false;
        } else {
            this.isMarked = isMarking;
            return true;
        }
    }

    /**
     * @inheritDoc.
     */
    @Override
    public String toString() {
        String mark = isMarked ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }
}
