import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String formatDate() {
        if (isValidFormat(this.by)) {
            LocalDate date = LocalDate.parse(this.by);
            String formatted = date.format(DateTimeFormatter.ofPattern("d MMM uuuu"));
            return formatted;
        } else {
            return this.by;
        }
    }

    public boolean isValidFormat(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toFileFormat() {
        return "D .. " + super.toFileFormat() + " .. " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }
}
