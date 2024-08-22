/**
 * Represents a task for the fishman bot.
 * This class contains the details of a task.
 */
class Task {
    /** The details of the task. */
    protected String detail;

    /**
     * Constructs a new Task object with the given detail.
     *
     * @param detail The details of the task.
     */
    public Task(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return detail;
    }
}
