package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime dueDate;

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

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDate() + ")";
    }
}

