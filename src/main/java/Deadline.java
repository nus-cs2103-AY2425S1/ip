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
        try {
            return LocalDate.parse(dateString, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateString);
            return null;
        }
    }

    public String dateToString() {
        if (deadline != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return deadline.format(outputFormatter);
        }
        return "No deadline set";
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateToString() + ")";
    }
    public String toFileString() {
        return "T | " + super.toFileString() + " | " + dateToString();
    }
}
