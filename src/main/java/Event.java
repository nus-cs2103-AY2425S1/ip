import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String name, String from, String to) {
        super(name);

        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getSaveFormat() {
        return "E | " + super.getSaveFormat() + " | " + this.from + " | " + this.to;
    }

    @Override
    public boolean isDuring(String date) {
        LocalDate localdate = LocalDate.parse(date);
        return !(localdate.isBefore(this.from) || localdate.isAfter(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)",
                        this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                        this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
