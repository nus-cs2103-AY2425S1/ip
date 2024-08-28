import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;
    
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, LocalDate from, LocalDate to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringFrom = this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String stringTo = this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "E," + done + "," + this.getName() + "," + stringFrom + "," + stringTo;
    }

    @Override
    public String toString() {
        String stringFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String stringTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E] " + super.toString() + " (from: " + stringFrom + " to: " + stringTo + ")";
    }
}
