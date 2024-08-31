package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Converts a LocalDateTime object to a string.
     *
     * @param dateTime
     * @return String
     */
    private static String dateTimeToString(LocalDateTime dateTime) {
        // Correct format: "MMM dd yyyy" e.g., (Oct 15 2019)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    /**
     * Constructor for Event.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event details.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeToString(from) + " to: " + dateTimeToString(to) + ")";
    }
}
