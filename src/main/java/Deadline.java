import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    String by;
    LocalDate newBy;

    public Deadline(String description, String by) {
        super(description);
        this.newBy = convertDate(by);
        this.by = by;
    }

    @Override
    public String toString() {
        String date = (newBy != null) ? newBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : by;
        return "[D]" + super.toString() + "(by: " + date + ")";
    }

    @Override
    public String changeFormat() {
        return "D | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    @Override
    public LocalDate convertDate(String date) {
        try {
            return LocalDate.parse(date.trim(), DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}