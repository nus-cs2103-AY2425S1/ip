import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = DateFormatters.getDateFromStr(from);
        this.to = DateFormatters.getDateFromStr(to);
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String type() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateFormatters.getStrFromDate(this.from) + " to: " + DateFormatters.getStrFromDate(this.to) + ")";
    }
}
