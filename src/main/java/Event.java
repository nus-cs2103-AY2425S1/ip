import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from, to;

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, "event", isDone);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E|" + isDone + "|" + description + "|" + from.toString() + "|" + to.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
