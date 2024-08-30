package yapper.app;

import yapper.exceptions.YapperException;

/**
 * Represents a deadline task in the Yapper chatbot application.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param desc the description of the deadline task
     * @param by   the date and/or time by which the task must be completed
     * @throws YapperException if the deadline is empty
     */
    public Deadline(String desc, String by) {
        super(desc);
        if (by.isEmpty()) {
            throw new YapperException("Description cannot be empty");
        }
        this.by = by;
    }

    /**
     * Returns a formatted description of the event meant an input into the save file.
     * and the formatted deadline.
     *
     * @return a string representing the formatted description of the deadline task
     */
    @Override
    public String getDesc() {
        return "| D | " + super.getDesc() + " | " + formattedDate(this.by);
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, description,
     * and the formatted deadline in a user-friendly format.
     *
     * @return a string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate(this.by) + ")";
    }
}
