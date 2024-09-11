package boombotroz;

/**
 * Deals with creation of task.
 */
public abstract class Task {
    private boolean mark;
    private String task;
    private int priority;

    /**
     * Creates Task object.
     *
     * @param mark state of completion for task.
     * @param task task description.
     */
    public Task(boolean mark, String task, int priority) {
        this.mark = mark;
        this.task = task;
        this.priority = priority;
    }


    /**
     * Sets completion state of the task.
     *
     * @param mark completion state of task.
     */
    public void setMark(boolean mark) {
        this.mark = mark;
    }

    /**
     * Gets the priority level of the task.
     */
    public int getPrior() {
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
        if (this.mark == true) {
            s = String.format("[X][%d] %s", this.priority, this.task);
        } else {
            s = String.format("[ ][%d] %s", this.priority, this.task);
        }
        return s;

    }

}
