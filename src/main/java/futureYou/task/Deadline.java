package futureyou.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that extends the basic Task class.
 * The deadline task has a name, a specific deadline date and time, and a completion status.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified name and deadline date.
     *
     * @param name The name of the deadline task.
     * @param date The deadline date and time for the task.
     */
    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.deadline = date;
    }

    /**
     * Constructs a Deadline task with the specified name, completion status, and deadline date.
     *
     * @param name       The name of the deadline task.
     * @param taskStatus The completion status of the deadline task.
     * @param date       The deadline date and time for the task.
     */
    public Deadline(String name, Boolean taskStatus, LocalDateTime date) {
        super(name, taskStatus);
        this.deadline = date;
    }

    /**
     * Returns the formatted deadline date and time of the task.
     * The format used is "d MMM yyyy - HH:mm".
     *
     * @return The formatted deadline date and time as a string.
     */
    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, which is "D" for a deadline.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task formatted for storage.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        // The format is: "D|<done>|<taskName>|<deadline>", where <done> is 1 if completed, 0 otherwise.
        return super.toString() + "|" + this.getDeadline();
    }

    /**
     * Returns a formatted string representation of the deadline task for display.
     *
     * @return A formatted string for displaying the deadline task.
     */
    @Override
    public String print() {
        //The format is: "[D] [X] <taskName> (by: <deadline>)", where [X] is marked if completed.
        return super.print() + " (by: " + this.getDeadline() + ")";
    }
}
