import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toFormatted() {
        return "E," + this.isDone() + "," + this.description + "," + this.from + "," + this.to + "\n";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getDayOfMonth() + " " + from.getMonth() + " " + from.getYear() + " to: " + to.getDayOfMonth() + " " + to.getMonth() + " " + to.getYear() + ")";
    }
}
