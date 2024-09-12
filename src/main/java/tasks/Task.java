package tasks;

/**
 * Abstract Task class to common attributes and methods between concrete tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isMarked;

    /**
     * Constructor for Task class.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Marks or unmarks task.
     *
     * @return Success of marking or unmarking task.
     */
    public boolean mark() {
        // Tasks.Task is already marked correctly
        if (this.isMarked) {
            return false;
        } else {
            this.isMarked = true;
            return true;
        }
    }

    /**
     * Unmarks task.
     *
     * @return Success of marking or unmarking task.
     */
    public boolean unmark() {
        // Tasks.Task is already marked correctly
        if (!this.isMarked) {
            return false;
        } else {
            this.isMarked = false;
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
