package boombotroz;

/**
 * Deals with creation of task.
 */
public abstract class Task {
    private boolean isMark;
    private String task;
    private int priority;

    /**
     * Creates Task object.
     *
     * @param isMark state of completion for task.
     * @param task task description.
     * @param priority priority level of task.
     */
    public Task(boolean isMark, String task, int priority) {
        this.isMark = isMark;
        this.task = task;
        this.priority = priority;
    }

    /**
     * Checks if time for the tasks is in yyyy-mm-dd format.
     *
     * @param ui handles errors that may occur.
     * @throws BoomException If invalid deadline / time period given.
     */
    public abstract void hasDate(Ui ui) throws BoomException;

    /**
     * Sets completion state of the task.
     *
     * @param isMark completion state of task.
     */
    public void setMark(boolean isMark) {
        this.isMark = isMark;
    }

    /**
     * Gets the priority level of the task.
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Returns string representation of the task.
     */
    public String getDescription() {
        return this.task;
    }

    @Override
    public String toString() {
        String s;
        if (this.isMark == true) {
            s = String.format("[X][%d] %s", this.priority, this.task);
        } else {
            s = String.format("[ ][%d] %s", this.priority, this.task);
        }
        return s;

    }

}
