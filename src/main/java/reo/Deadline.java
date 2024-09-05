package reo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = stringToDate(deadline);
    }

    private LocalDate stringToDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String INVALID_DATE_ERROR = "Invalid date format: ";
        try {
            return LocalDate.parse(dateString, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println(INVALID_DATE_ERROR + dateString);
            return null;
        }
    }

    public String dateToString() {
        String NO_DEADLINE = "No deadline set";
        if (deadline != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return deadline.format(outputFormatter);
        }
        return NO_DEADLINE;
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateToString() + ")";
    }
    public String toFileString() {
        return "T | " + super.toFileString() + " | " + dateToString();
    }
}
