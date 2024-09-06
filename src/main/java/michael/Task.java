package michael;

public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Represents task as done.
     *
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Represents task as not done yet.
     *
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Returns true if task is done, and false otherwise.
     *
     * @return Boolean value corresponding to status of task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        }
        return "[ ] " + taskName;
    }
}
