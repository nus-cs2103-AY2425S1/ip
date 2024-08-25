package Task;

import java.time.LocalDate;
import static Utilities.DateTimeParser.formatDateForDisplay;

public class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    protected String getTaskType() {
        return "Event";
    }

    @Override
    public String formatToCSV() {
        String res = super.formatToCSV();
        res += DELIMITER + wrapInQuotes(formatDateForDisplay(start));
        res += DELIMITER + wrapInQuotes(formatDateForDisplay(end));
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
