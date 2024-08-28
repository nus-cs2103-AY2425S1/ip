package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate startDate;
    //public LocalDate dueDate;
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.dueDate = endDate;
    }
    @Override
    public String toBeSavedAsData() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate + " // to: " + dueDate;
    }
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString()
                + " // from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " // to: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
