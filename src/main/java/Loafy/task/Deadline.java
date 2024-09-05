package loafy.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task with a specified deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Constructs an undone deadline task with the specified name and deadline.
     *
     * @param name The name of the deadline task.
     * @param date The deadline of the task of type {@code LocalDateTime}.
     */
    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructs a deadline task with the specified status (done or undone), name and deadline.
     *
     * @param isDone The state of the task ({@code true} for done and {@code false} for undone).
     * @param name The name of the deadline task.
     * @param date The deadline of the task of type {@code LocalDateTime}.
     */
    public Deadline(boolean isDone, String name, LocalDateTime date) {
        super(isDone, name);
        this.date = date;
    }

    /**
     * Returns a string representation of this deadline task for user view.
     * The string representation is in the format "[D][status] name (by: date)",
     * "[D]" symbolises type of task (deadline);
     * status is represented by "X" if task is done and " " if task is undone;
     * and date is in the format "d/M/yyyy HHmm".
     *
     * @return a string representation of this deadline task.
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format(
                "[D]%s (by: %s)",
                super.toString(), this.date.format(formatter));
    }

    /**
     * Returns a string representation of this deadline task for data storing.
     * The string representation is in the format "type,status,name,deadline", where:
     * type = "D";
     * status = "true" if task is done and "false" if task is not done;
     * name is exactly the name of the task;
     * deadline is in "d/M/yyyy HHmm" format.
     *
     * @return a string representation of this deadline task.
     */
    public String convertToTxt() {
        return String.format("%s,%s,%s","D", super.convertToTxt(), this.date);
    }
}
