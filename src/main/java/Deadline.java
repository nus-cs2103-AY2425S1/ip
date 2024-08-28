import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.D);
        this.by = by;
    }

    public String changeDateFormat(String by) {
        if (by == null || by.trim().isEmpty()) {
            System.out.println("The deadline cannot be empty!");
            return "Invalid date";
        }

        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + by);
            return "Invalid date";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + changeDateFormat(by) + ")";
    }
}
