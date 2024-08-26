package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected LocalDate startDate;
    protected LocalDate endDate;

    // Constructor for LocalDateTime
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.startDateTime = fromDateTime;
        this.endDateTime = toDateTime;
    }

    // Constructor for LocalDate
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.startDate = fromDate;
        this.endDate = toDate;
    }

    public String[] format() {
        if (startDateTime != null && endDateTime != null) {
            return new String[]{
                    startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                    endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            };
        } else if (startDate != null && endDate != null) {
            return new String[]{
                    startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            };
        }
        return new String[]{};
    }

    public LocalDate getStartDate() {
        if (startDate != null) {
            return startDate;
        }
        return startDateTime.toLocalDate();
    }

    public LocalDate getEndDate() {
        if (endDate != null) {
            return endDate;
        }
        return endDateTime.toLocalDate();
    }

    @Override
    public String toSave() {
        return "E" + super.toSave() + " | " + format()[0] + " | " + format()[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + format()[0] + " to: " + format()[1] + ")";
    }


    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
