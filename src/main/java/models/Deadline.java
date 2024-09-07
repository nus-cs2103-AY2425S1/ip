package models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description) {
        super(description);
    }

    public LocalDate convertDate(String potentialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(potentialDate, formatter);
        } catch (DateTimeParseException e) {// Handle parsing errors
            return LocalDate.EPOCH;  // Or handle as needed
        }
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = convertDate(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = convertDate(by);
    }

    public LocalDate getBy() {
        return this.by;
    }
    public String serialize() {
        return String.format("D|%s|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription(),
                this.getBy());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.getStatusIcon(), this.getDescription(), this.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}