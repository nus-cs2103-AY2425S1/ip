import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    // make it MMM dd yyyy format
    public String getDateString(LocalDate date) {
        return date.getMonth().toString().substring(0, 1) + date.getMonth().toString().substring(1, 3).toLowerCase()
                + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + getDateString(this.from)
                + " to: " + getDateString(this.to) + ")";
    }
}