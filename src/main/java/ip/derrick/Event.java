package ip.derrick;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event containing both a start time and end time.
 */
public class Event extends Task {

    private String start;
    private String end;
    private LocalDate newStart;
    private LocalDate newEnd;

    /**
     * Initializes the event with a description, start time and end time.
     * @param description Description of the event
     * @param start Start time of the event
     * @param end End time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.newStart = convertDate(start);
        this.newEnd = convertDate(end);
    }

    @Override
    public String toString() {
        String startDate = (newStart != null) ? newStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : start;
        String endDate = (newEnd != null) ? newEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) : end;
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String changeFormat() {
        return "E | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | "
                + this.start + " | " + this.end;
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
