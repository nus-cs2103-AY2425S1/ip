package cloud.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task object
     *
     * @param desc   description of the task
     * @param isDone completion status of the task
     */
    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    /**
     * Constructs a task object that is not completed
     *
     * @param desc description of the task
     */
    public Task(String desc) {
        this(desc, false);
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task formatted for saving
     *
     * @return formatted string representation of the task
     */
    public String formatSave() {
        return (isDone ? "1" : "0")
                + " | "
                + this.description
                + "\n";
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.isDone ? "X" : " ",
                this.description
        );
    }
}
