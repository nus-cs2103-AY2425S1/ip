package terminator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;
    public EventTask(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description, TaskType.EVENT);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String fromDateString = fromDate.format(formatter);
        String toDateString = toDate.format(formatter);
        return super.toString() + " from: " + fromDateString + " to: " + toDateString;
    }

    @Override
    public String getDataRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        return super.getDataRepresentation()
                + this.fromDate.format(formatter) + " "
                + this.toDate.format(formatter) + " "
                + this.description;
    }
}
