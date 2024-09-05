package loafy.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(boolean isDone, String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(isDone, name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(), this.startDate.format(formatter), this.endDate.format(formatter));
    }

    public String convertToTxt() {
        return String.format("%s,%s,%s,%s","E", super.convertToTxt(), this.startDate, this.endDate);
    }
}