package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the description of the task, including its status and description.
     *
     * @return A string description of the task.
     */
    public String getDescription() {
        return this.description;
    }


}