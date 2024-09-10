package vuewee.task;

/**
 * Represents a task with a description, marked status, and type. It is an
 * abstract class that has to implement a method to serialize and deserialize
 * the task from file strings.
 */
public abstract class Task {
    static final String DELIMETER = "|";
    static final String DELIMETER_SPACE = " " + DELIMETER + " ";

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    protected Task() {
    };

    protected Task(String description, TaskType type) {
        this(description, type, false);
    }

    protected Task(String description, TaskType type, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    abstract void deserialize(String serializedTask);

    abstract String serialize();

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks if the date is within the task's date range.
     *
     * @param date
     * @return true if the date is within the task's date range, false otherwise
     */
    public abstract boolean isWithinDateRange(TaskLocalDate date);

    /**
     * Marks the task as completed.
     *
     * @return true if the task value was changed, false otherwise
     */
    public boolean markAsDone() {
        boolean isSuccessful = !this.isDone;
        this.isDone = true;
        return isSuccessful;
    }

    /**
     * Marks the task as not completed.
     *
     * @return true if the task value was changed, false otherwise
     */
    public boolean markAsUndone() {
        boolean isSuccessful = this.isDone;
        this.isDone = false;
        return isSuccessful;
    }

    @Override
    public String toString() {
        return "[" + this.type.toChar() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
