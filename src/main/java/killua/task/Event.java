package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    // Constructor for LocalDateTime
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    // Constructor for LocalDate
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private String[] format() {
        if (fromDateTime != null && toDateTime != null) {
            return new String[]{
                    fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                    toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            };
        } else if (fromDate != null && toDate != null) {
            return new String[]{
                    fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                    toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            };
        }
        return new String[]{};
    }

    public LocalDate getStartDate() {
        if (fromDate != null) {
            return fromDate;
        }
        return fromDateTime.toLocalDate();
    }

    public LocalDate getEndDate() {
        if (toDate != null) {
            return toDate;
        }
        return toDateTime.toLocalDate();
    }

    @Override
    public String toSave() {
        return "E" + super.toSave() + " | " + format()[0] + " | " + format()[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + format()[0] + " to: " + format()[1] + ")";
    }


}
