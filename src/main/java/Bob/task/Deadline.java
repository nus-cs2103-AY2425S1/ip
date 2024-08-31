package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;

    private static String dateTimeToString(LocalDateTime dateTime) {
        // Correct format: "MMM dd yyyy" e.g., (Oct 15 2019)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeToString(by) + ")";
    }
}
