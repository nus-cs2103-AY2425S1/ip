package lutodo.tasks;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task. All types of tasks inherit from this class.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /** Constructs an event task with task information.
     * @param description Containing task information.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
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
     * Get the icon representing the status of the task, 'X' means done, '_' means to be done.
     *
     * @return The icon representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "_"); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return  The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Parses the date message to the date.
     *
     * @param dateMessage the String message of the date
     * @return LocalDate parsed from the message.
     */
    public static LocalDate manageDate(String dateMessage) {
        return LocalDate.parse(dateMessage,DateTimeFormatter.ISO_DATE);
    }

    /**
     * Parses the time message to the time.
     *
     * @param timeMessage the String message of the time.
     * @return LocalTime parsed from the message.
     */
    public static LocalTime manageTime(String timeMessage) {
        return LocalTime.parse(timeMessage,DateTimeFormatter.ISO_TIME);
    }

    /**
     * Returns The String describing the task.
     *
     * @return The String describing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
