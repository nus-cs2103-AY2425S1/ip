package tasks;

import java.io.Serializable;

/**
 * Abstract Task class to common attributes and methods between concrete tasks.
 */
// Solution below adapted from https://stackoverflow.com
// /questions/13895867/why-does-writeobject-throw-java-io-notserializableexception-and-how-do-i-fix-it
public abstract class Task implements Serializable {
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
     * @return Success of marking task.
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
     * @return Success of unmarking task.
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
    public boolean equals(Object task) {
        if (task instanceof Task) {
            return this.description.equals(((Task) task).description);
        }
        return false;
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
