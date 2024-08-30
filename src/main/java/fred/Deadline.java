package fred;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    DateTimeFormatter formatter;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }

    @Override
    public String getDataFormat() {
        return "D" + super.getDataFormat() + " | " + by.format(formatter);
    }
}

