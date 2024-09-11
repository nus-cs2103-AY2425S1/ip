package knight2103.tasks;

/**
 * Contains the basic requirements of what makes a task.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task object which contains a description of the task.
     * The task object by default has the completion status set as not done.
     *
     * @param description The description of the task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates if the task is marked as done.
     *
     * @return isDone status.
     */
    public boolean isDone() {
        return this.isDone;
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
        final String TASK_MARKED_FILE_STATUS = "1";
        final String TASK_UNMARKED_FILE_STATUS = "0";
        return String.format("| %s | %s",
                this.isDone ? TASK_MARKED_FILE_STATUS : TASK_UNMARKED_FILE_STATUS, this.description);
    }

    @Override
    public String toString() {
        final String TASK_MARKED_LIST_STATUS = "X";
        final String TASK_UNMARKED_LIST_STATUS = " ";
        return String.format("[%s] %s",
                this.isDone ? TASK_MARKED_LIST_STATUS : TASK_UNMARKED_LIST_STATUS, this.description);
    }

    public abstract TaskType showTaskType();
}
