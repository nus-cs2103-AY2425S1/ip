package michael;

public class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    /**
     * Represents task as done.
     *
     */
    public void doTask() {
        this.done = true;
    }

    /**
     * Represents task as not done yet.
     *
     */
    public void undoTask() {
        this.done = false;
    }

    /**
     * Returns true if task is done, and false otherwise.
     *
     * @return Boolean value corresponding to status of task.
     */
    public boolean isDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
