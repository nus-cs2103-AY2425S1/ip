package task;

/**
 * Contains the name of the task and the status of the task (done/not done).
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Task constructor method.
     *
     * @param task Task name.
     */
    public Task(String task) {
        taskName = task;
        isDone = false;
    }

    public abstract String toStorageFormat();

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return (isDone);
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return ("[" + status + "] " + taskName);
    }
}
