package com.nimbus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a Task with a deadline
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    /**
     * Create new deadline object
     * @param description Description of the deadline
     * @param isDone set the task to be done
     * @param deadline Deadline in string format
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        try {
            LocalDate date = LocalDate.parse(deadline);
            this.deadline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toFileFormat() {
        String isDoneString = this.isDone ? "1" : "0";
        String commandHeader = getTypeIcon().charAt(1) + "|" + isDoneString;
        return commandHeader + "|" + this.getDescription() + "|" + this.getDeadline();
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public boolean contains(String keyword) {
        return getDescription().contains(keyword);
    }
}
