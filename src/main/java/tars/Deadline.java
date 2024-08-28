package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Creates a new Deadline task with a specified name, completion status, and due date.
     * The date is parsed using the {@link DateTimeParser} class. If the date cannot be parsed,
     * a {@link TarsException} is thrown.
     *
     * @param name The name or description of the deadline task.
     * @param done Indicates whether the task is completed or not.
     * @param date The due date of the task in the format "yyyy-MM-dd HHmm" or "dd MMM yyyy, HH:mm".
     * @throws TarsException If the provided date cannot be parsed into a {@link java.time.LocalDateTime}.
     */
    public Deadline(String name, boolean done, String date) throws TarsException {
        super(name, done);
        System.out.println("Creating Deadline task with date: " + date);
        try {
            this.dueDate = DateTimeParser.parse(date);
            System.out.println("Successfully parsed date for Deadline: " + this.dueDate);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date for Deadline: " + date);
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public String getDate() {
        return DateTimeParser.format(this.dueDate);
    }

    public void changeDate(String newDate) throws TarsException {
        try {
            this.dueDate = DateTimeParser.parse(newDate);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns a string representation of this deadline task.
     * The format of the string is "[D] [task description] (by: [due date])".
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDate() + ")";
    }
}

