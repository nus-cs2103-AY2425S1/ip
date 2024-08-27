import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private String formatDate(String input) {
        if (isValidFormat(input)) {
            LocalDate date = LocalDate.parse(input);
            String formatted = date.format(DateTimeFormatter.ofPattern("d MMM uuuu"));
            return formatted;
        } else {
            return input;
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
        return "E .. " + super.toFileFormat() + " .. " + this.from + " .. " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to: " + formatDate(this.to) + ")";
    }
}
