package yapper.components;

import yapper.exceptions.YapperException;

/**
 * Represents a deadline task in the Yapper chatbot application.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description the description of the deadline task
     * @param deadline   the date and/or time by which the task must be completed
     * @throws YapperException if the deadline is empty
     */
    public Deadline(String description, String deadline) {
        super(description);
        if (deadline.isEmpty()) {
            throw new YapperException("Description cannot be empty");
        }
        this.deadline = deadline;
    }

    /**
     * Returns a formatted description of the event meant an input into the save file.
     * and the formatted deadline.
     *
     * @return a string representing the formatted description of the deadline task
     */
    @Override
    public String getDesc() {
        return "| D | " + super.getDesc() + " | " + formattedDate(this.deadline);
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, description,
     * and the formatted deadline in a user-friendly format.
     *
     * @return a string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate(this.deadline) + ")";
    }
}
