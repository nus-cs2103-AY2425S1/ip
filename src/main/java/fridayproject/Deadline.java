package fridayproject;

import java.lang.Object;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Represents a deadline task.
 * A deadline task has a description and a date.
 */
public class Deadline extends Tasks {
    protected LocalDate date;

    /*
     * Constructor for a deadline task.
     * @param description The description of the deadline task.
     * @param date The date of the deadline task.
     * Example: [D][ ] return book (by: Sep 30 2021)
     */
    public Deadline(String description, String date) {
        super(description);

        // Assertions to ensure that the description and date are not null or empty
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty.";
        assert date != null && !date.trim().isEmpty() : "Date should not be null or empty.";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);

        // Assertion to ensure that the date is parsed correctly
        assert this.date != null : "Date should be parsed correctly";
    }

    /* 
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() + " (by: " + date.format(formatter) + ")";
    }

    /*
     * Returns the string representation of the task to be saved in the file.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /*
     * Returns the string representation of the task to be saved in the file.
     */
    @Override 
    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.date;
    }
}
