package duke;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        int startDay = this.start.getDayOfMonth();
        Month startMonth = this.start.getMonth();
        int startYear = this.start.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String startTime12HourFormat = this.start.format(formatter);
        String startDate = startDay + " " + startMonth + " " + startYear + " " + startTime12HourFormat;

        int endDay = this.end.getDayOfMonth();
        Month endMonth = this.end.getMonth();
        int endYear = this.end.getYear();
        String endTime12HourFormat = this.end.format(formatter);
        String endDate = endDay + " " + endMonth + " " + endYear + " " + endTime12HourFormat;

        return this.getTypeIcon() + super.toString() + " (from: " + startDate + " " +
                "to: " + endDate + ")";
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}
