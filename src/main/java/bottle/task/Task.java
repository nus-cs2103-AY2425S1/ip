package bottle.task;

/**
 * The type Task.
 */
public abstract class Task {

    /**
     * The Is checked.
     */
    protected boolean isChecked;
    /**
     * The Task desc.
     */
    protected String taskDesc;

    /**
     * Instantiates a new Task.
     *
     * @param taskDesc the task desc
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isChecked = false;
    }

    /**
     * Mark.
     */
    public void mark() {
        this.isChecked = true;
    }

    /**
     * Un mark.
     */
    public void unMark() {
        this.isChecked = false;
    }

    @Override
    public String toString() {
        String box = isChecked ? "[X] " : "[ ] ";
        return box + taskDesc;
    }

    /**
     * To save format string.
     *
     * @return the string
     */
    public abstract String toSaveFormat();
}
