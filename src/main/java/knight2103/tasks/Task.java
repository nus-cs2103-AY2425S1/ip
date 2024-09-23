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
     * @throws IllegalArgumentException If the task description are all white space characters or empty
     */
    Task(String description) throws IllegalArgumentException {
        this.description = description;
        this.isDone = false;

        if (checkHasWhiteSpaceOnly(this.description) || this.description.isEmpty()) {
            throw new IllegalArgumentException(
                    "Error: Task description must contain non-white space characters");
        }
    }

    private boolean checkHasWhiteSpaceOnly(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
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
     * Marks the task as not done (unmark).
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
                this.isDone ? TASK_MARKED_FILE_STATUS : TASK_UNMARKED_FILE_STATUS,
                this.description);
    }

    @Override
    public String toString() {
        final String TASK_MARKED_LIST_STATUS = "X";
        final String TASK_UNMARKED_LIST_STATUS = " ";
        return String.format("[%s] %s",
                this.isDone ? TASK_MARKED_LIST_STATUS : TASK_UNMARKED_LIST_STATUS,
                this.description);
    }

    /**
     * Returns the TaskType of the object.
     *
     * @return TaskType of the task object.
     */
    public abstract TaskType showTaskType();
}
