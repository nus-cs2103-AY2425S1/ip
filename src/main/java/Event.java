package ip.derrick ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    String start;
    String end;
    LocalDate newStart;
    LocalDate newEnd;
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
