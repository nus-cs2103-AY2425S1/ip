package reo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents the Task type Deadline */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Acts as the constructor for the Deadline class.
     *
     * @param name The name of the deadline task.
     * @param isDone The completion status of the deadline task.
     * @param deadline The raw unformatted deadline given by user.
     */
    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = stringToDate(deadline);
    }

    /**
     * Converts a String (in a specified format) to a date object.
     *
     * @param dateString The string representation of the date.
     * @return The date representation of the given string.
     */
    private LocalDate stringToDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString, inputFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Converts the user's raw deadline input into a formatted string.
     *
     * @return The formatted string representation of the given string.
     */
    public String dateToString() {
        String NO_DEADLINE = "No deadline set";
        if (deadline == null) {
            return null;
        }
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return deadline.format(outputFormatter);
    }

    /**
     * Acts as the toString method for the Deadline class.
     *
     * @return The string representation of the event to be displayed to user.
     */
    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateToString() + ")";
    }

    /**
     * Returns the line to be written to the file to represent the deadline object.
     *
     * @return The string representation of the deadline to be written to memory.
     */
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + dateToString();
    }
}
