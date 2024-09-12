package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Deadline class represents a task with a specific deadline.
 * It supports deadlines specified as either a date or a date-time.
 */
public class Deadline extends Task {

    private LocalDateTime timeDeadline;
    private LocalDate dateDeadline;

    /**
     * Constructs a Deadline with a name and a LocalDateTime deadline.
     *
     * @param name     The name or description of the task.
     * @param deadline The deadline as a LocalDateTime.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.timeDeadline = deadline;
    }

    /**
     * Constructs a Deadline with a name and a LocalDate deadline.
     *
     * @param name     The name or description of the task.
     * @param deadline The deadline as a LocalDate.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.dateDeadline = deadline;
    }

    /**
     * Constructs a Deadline with a name, a LocalDateTime deadline, and a completion status.
     *
     * @param name     The name or description of the task.
     * @param deadline The deadline as a LocalDateTime.
     * @param isDone   Whether the task is marked as completed.
     */
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.timeDeadline = deadline;
    }

    /**
     * Constructs a Deadline with a name, a LocalDate deadline, and a completion status.
     *
     * @param name     The name or description of the task.
     * @param deadline The deadline as a LocalDate.
     * @param isDone   Whether the task is marked as completed.
     */
    public Deadline(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.dateDeadline = deadline;
    }

    /**
     * Formats the deadline for display purposes.
     *
     * @return The formatted deadline as a String.
     */
    private String getDisplayStringDeadline() {
        if (this.timeDeadline != null) {
            return this.timeDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else {
            return this.dateDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    /**
     * Formats the deadline for saving to a file.
     *
     * @return The formatted deadline as a String.
     */
    private String getStringDeadline() {
        if (this.timeDeadline != null) {
            return this.timeDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return this.dateDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    /**
     * Converts the Deadline task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringDeadline = this.getStringDeadline();
        return "D," + done + "," + this.getName() + "," + stringDeadline;
    }

    /**
     * Returns the string representation of the Deadline task, including its deadline.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String stringDeadline = this.getDisplayStringDeadline();
        return "[D] " + super.toString() + " (by: " + stringDeadline + ")";
    }
}
