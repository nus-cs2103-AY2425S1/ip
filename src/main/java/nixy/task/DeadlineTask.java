package nixy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter SAVE_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate deadline;

    /**
     * Creates a deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task with the specified description, deadline and done status.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The done status of the task.
     */
    public DeadlineTask(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the type label of the task.
     *
     * @return The type label of the task.
     */
    private String getTypeLabel() {
        return "D";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the deadline label of the task.
     *
     * @return The deadline label of the task.
     */
    private String getDeadlineLabel() {
        return String.format("(by: %s)", this.deadline.format(DATE_TIME_FORMATTER));
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTypeLabel(), super.toString(), this.getDeadlineLabel());
    }

    /**
     * Returns the string representation of the task in data format (for saving to file)
     *
     * @return The data string.
     */
    @Override
    public String toDataString() {
        return String.format("%s|%s|%s", this.getTypeLabel(), super.toDataString(),
            this.deadline.format(SAVE_DATE_TIME_FORMATTER));
    }
}
