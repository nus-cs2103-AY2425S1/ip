package lebron;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline task includes a description and a deadline date.
 */
public class Deadlines extends Task {

    protected LocalDate deadline;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param deadline The deadline date of the task.
     */
    public Deadlines(String description, LocalDate deadline) {
        super(description);

        this.deadline = deadline;
    }

    public void reschedule(LocalDate newDeadline) {
        this.deadline = newDeadline;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the task type, status icon, description, and formatted deadline date.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcons(), super.description,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the deadline task formatted for file storage.
     * The string includes the task type, completion status, description, and the raw deadline date.
     *
     * @return A string representing the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", this.done ? 1 : 0, this.description, this.deadline);
    }

}
