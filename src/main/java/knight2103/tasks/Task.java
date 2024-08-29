package knight2103.tasks;

public class Task {
    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the output in String as a representation of Task object specifically in the storage file.
     *
     * @return String representation of Task object specifically in storage File.
     */
    public String saveToFileFormat() {
        return String.format("| %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the output in String as a representation of Task object.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.description);
    }
}
