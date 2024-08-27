package rizzler;

/**
 * Represents a to-do to be done.
 * Inherits from <code>Task</code>.
 */
class Todo extends Task {

    /**
     * Constructs a new <code>Todo</code>.
     *
     * @param name Name of the to-do.
     */
    Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new <code>Todo</code>
     * but specifying if the to-do is completed.
     * This is explicitly for <code>FileStorage</code> to load
     * tasks saved in the file.
     *
     * @param name Name of the to-do.
     * @param isDone If the to-do is completed or not.
     */
    Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a String representation of the to-do.
     *
     * @return String that represents the to-do.
     */
    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] "
                + this.name;
    }

    /**
     * Returns a String representation of the to-do for
     * <code>FileStorage</code> to save to the file.
     *
     * @return String that represents the to-do in the save file.
     */
    @Override
    String toSaveState() {
        return "T" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "\n";
    }
}
