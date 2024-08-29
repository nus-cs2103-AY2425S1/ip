import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    private String dateStringRepresentation() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateStringRepresentation() + ")";
    }
}