package deadline;
import task.Task;

/**
 * Represents a task with a deadline. A deadline task has a name and a date/time by which the task is due.
 * <p>
 * Inherits from the {@link Task} class.
 * </p>
 */
public class Deadline extends Task {
    private String dateTime;

    /**
     * Constructs a {@code Deadline} task with the specified name and date/time.
     *
     * @param name The name of the task.
     * @param dateTime The date and time by which the task is due.
     */
    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (super.isDone()) {
            str += "[X]";
        } else {
            str += "[ ]";
        }
        str += (" " + super.getName() + " (by: " + this.dateTime + ")\n");
        return str;
    }

    /**
     * Returns the date and time by which the task is due.
     *
     * @return The date and time of the deadline.
     */
    public String getBy() {
        return this.dateTime;
    }
}
