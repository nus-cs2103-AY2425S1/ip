package Boombotroz;

/**
 * Deals with creation of task.
 */
public abstract class Task {
    boolean mark;
    String task;

    public Task(boolean mark, String task) {
        this.mark = mark;
        this.task = task;

    }

    /**
     * Checks if time for the tasks is in yyyy-mm-dd format.
     *
     * @param ui handles errors that may occur.
     * @throws BoomException If invalid deadline / time period given.
     */
    public abstract void hasDate(Ui ui) throws BoomException;

    /**
     * Returns string representation of the task.
     */
    @Override
    public String toString() {
        String s;
        if (this.mark == true) {
            s = String.format("[X] %s", this.task);
        } else {
            s = String.format("[ ] %s", this.task);
        }
        return s;

    }

}
