package talkie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Talkie application.
 * <p>
 * A {@code Deadline} task has a description and a deadline date/time by which the task needs to be completed.
 * </p>
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} with the specified description and deadline date/time.
     *
     * @param desc The description of the deadline task.
     * @param by The deadline date/time by which the task needs to be completed.
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * Serializes the deadline task to a string format for storage or retrieval.
     * <p>
     * The string format is as follows: "D | status | description | deadline date/time".
     * </p>
     *
     * @return A string representation of the deadline task for storage.
     */
    @Override
    public String stringifyTask() {
        return String.format("D | %d | %s | %s", super.getStatus() ? 1 : 0,
                super.getDesc(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The string includes the task's status icon, description, and the deadline date/time in the format "MMM dd yyyy HH:mm".
     * </p>
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }
}
