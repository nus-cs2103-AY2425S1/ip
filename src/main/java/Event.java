import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate startDate;
    public LocalDate endDate;
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toBeSavedAsData() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate + " // to: " + endDate;
    }
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " // to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
