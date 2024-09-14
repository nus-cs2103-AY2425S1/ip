package ynch.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that extends the Task class.
 */
class Deadline extends Task {
    LocalDate deadline;
    static DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param task     the description of the deadline task
     * @param deadline the deadline date of the task
     */
    Deadline(String task, String deadline) {
        super(task);
        this.deadline = super.stringToDate(deadline);
    }

    /**
     * Constructs a new Deadline task with the specified status, description, and deadline.
     *
     * @param status   the completion status of the deadline task (1 for done, 0 for not done)
     * @param task     the description of the deadline task
     * @param deadline the deadline date of the task
     */
    Deadline(int status, String task, String deadline) {
        super(status, task);
        this.deadline = super.stringToDate(deadline);
    }

    boolean needsReminder() {
        LocalDate today = LocalDate.now();
        LocalDate dateToRemind = this.deadline.minusDays(DAYS_FOR_REMINDER);
        return today.isAfter(dateToRemind);
    }

    /**
     * Returns a string representation of the Deadline task, including its completion status and deadline.
     *
     * @return a string indicating the type of task, its status, and the deadline date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DATEFORMAT) + ")";
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving to a file.
     *
     * @return a string representing the type of task, its status, description, and deadline date
     */
    @Override
    String toSaveAsString() {
        return "D" + super.toSaveAsString() + "/" + this.deadline.toString();
    }
}