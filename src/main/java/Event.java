import java.time.LocalDate;

public class Event extends Task{
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String name, String fromDate, String toDate) {
        super(name);
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[E]%s (from %s to: %s)",
                temp, fromDate.format(super.FORMATTER), toDate.format(super.FORMATTER));
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("E|%s|%s|%s", temp, fromDate, toDate);
    }
}
