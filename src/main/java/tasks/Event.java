package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");

    public Event(String task, LocalDate startDate, LocalDate endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%s | Event from %s to %s", super.toString(), outputFormatter.format(this.startDate), outputFormatter.format(this.endDate));
    }

    public LocalDate getStart() {
        return startDate;
    }

    public LocalDate getEnd() {
        return endDate;
    }
}