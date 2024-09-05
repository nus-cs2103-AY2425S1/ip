import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, "deadline", isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D|" + isDone + "|" + description + "|" + by.toString()  + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
