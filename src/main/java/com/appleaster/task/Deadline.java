package com.appleaster.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Appleaster application.
 * This class extends the Task class and adds a deadline attribute.
 */
public class Deadline extends Task {
    /** The deadline of the task */
    protected LocalDateTime by;
    
    /** The formatter for parsing input date strings */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    
    /** The formatter for formatting output date strings */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline of the task in the format "yyyy-MM-dd HHmm"
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Gets the deadline of the task.
     *
     * @return the deadline as a LocalDateTime object
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return a string representation of the task, including its type, status, description, and deadline
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type.getSymbol(), status.getSymbol(), description, by.format(OUTPUT_FORMAT));
    }
}