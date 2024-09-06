package fridayproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Represents a deadline task.
 */
public class Deadline extends Tasks {
    protected LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() + " (by: " + date.format(formatter) + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override 
    public String toFileString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.date;
    }
}
