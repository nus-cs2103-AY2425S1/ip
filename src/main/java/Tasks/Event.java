package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String name, LocalDate startTime, LocalDate endTime, boolean done) {
        super(name, done);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "E" + super.toData() + "%" + this.startTime + "|" + this.endTime;
    }
}
