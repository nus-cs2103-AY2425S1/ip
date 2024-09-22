package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import puke.exceptions.WrongDateTimeFormatException;

/**
 * Represents a deadline task with a description, completion status, and a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description, completion status, and due date.
     *
     * @param description the description of the deadline task
     * @param isDone true if the task is completed, false otherwise
     * @param by the due date of the deadline in the format "dd/MM/yyyy HHmm"
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        if (isDone) {
            markAsDone();
        }
    }

    /**
     * Compares this deadline to another object for equality.
     *
     * @param obj the object to compare this deadline to
     * @return true if the object is equal to this deadline, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return Objects.equals(by, deadline.by);
    }

    /**
     * Returns a string representation of the deadline, including its type indicator and due date.
     *
     * @return a string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(TIME_FORMATTER) + ")";
    }

    /**
     * Converts the deadline to its file format representation.
     *
     * @return the string representation of the deadline in file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Sets the due date of this deadline task.
     * The provided date string must follow the format "dd/MM/yyyy HHmm".
     *
     * @param by the new due date in the format "dd/MM/yyyy HHmm".
     * @throws WrongDateTimeFormatException if the provided date string is not in the correct format.
     */
    public void setBy(String by) throws WrongDateTimeFormatException {
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException("dd/MM/yyyy HHmm");
        }
    }

}
