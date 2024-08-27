package vuewee.task;

/**
 * Represents a task with a description, marked status, and type. It is an
 * abstract class that has to implement a method to serialize and deserialize
 * the task from file strings.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    final static String DELIMETER = "|";
    final static String DELIMETER_SPACE = " " + DELIMETER + " ";

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

    public boolean markAsDone() {
        boolean isSuccessful = !this.isDone;
        this.isDone = true;
        return isSuccessful;
    }

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
