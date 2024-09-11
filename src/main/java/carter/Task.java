package carter;

/**
 *  Represents an abstract task in Carter
 */
public abstract class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String description;

    /**
     * Construct a new Task with specified description.
     * The task is initially marked not done.
     *
     * @param description The description of task.
     */
    public Task(String description) {
        assert description != null : "description should not be null";

        this.isDone = false;
        this.description = description.trim();
    }

    /**
     * Construct a new Task with specified description and completion status.
     *
     * @param description The description of task.
     * @param isDone The completion status of task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "description should not be null";

        this.isDone = isDone;
        this.description = description.trim();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return A string representation of task.
     */
    private String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Return a string representation of the task,
     * including it description and completion status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     * This method must be implemented by subclass.
     *
     * @return A string representation of the task formatted for file storage.
     */
    public abstract String toFileString();

    @Override
    public int compareTo(Task other) {
        if (this instanceof ToDo) {
            return compareWithTodo(other);
        } else if (this instanceof Deadline) {
            return compareWithDeadline(other);
        } else if (this instanceof Event) {
            return compareWithEvent(other);
        } else {
            return 0;
        }
    }

    private int compareWithTodo(Task other) {
        return (other instanceof ToDo) ? 0 : -1;
    }

    private int compareWithDeadline(Task other) {
        if (other instanceof ToDo) {
            return 1;
        } else if (other instanceof Deadline) {
            return ((Deadline) this).by.compareTo(((Deadline) other).by);
        } else {
            return -1;
        }
    }

    private int compareWithEvent(Task other) {
        if (other instanceof Event) {
            return ((Event) this).from.compareTo(((Event) other).from);
        } else {
            return 1;
        }
    }
}
