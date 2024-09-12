package noisy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String formatTask() {
        String status = isDone? "1" : "0";
        return "E | " + this.description + " | " + status + " | " + this.startDate + " | " + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}