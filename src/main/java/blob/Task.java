package blob;

/**
 * Represents a Task that contains the following fields, a String representation of its type,
 * a name, and a boolean value of whether it is done or not.
 */
public class Task {
    protected String type;
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets isDone field to true
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Sets isDone field to false
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Returns the corresponding representation of the task's completion status.
     * @return "X" for done, and " " for not done.
     */
    public String check() {
        return isDone ? "X" : " ";
    }

    /**
     * @return String representation of the task of form "['completion status'] 'task name'"
     */
    @Override
    public String toString() {
        return "[" + this.check() + "] " + this.name;
    }
}
