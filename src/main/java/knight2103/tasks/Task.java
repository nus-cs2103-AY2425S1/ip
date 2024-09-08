package knight2103.tasks;

public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task object which contains a description of the task.
     * The task object by default has the completion status set as not done.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
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
    public String toStringInFile() {
        return String.format("| %d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.description);
    }
}
