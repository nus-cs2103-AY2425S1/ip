package Task;

import java.time.LocalDate;
import static Utilities.DateTimeParser.formatDateForDisplay;
import static Utilities.DateTimeParser.formatDateForStorage;

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
    public LocalDate getRelevantDate() {
        return this.start; // Or the most relevant date for your use case
    }

    @Override
    public String formatToCSV() {
        String res = super.formatToCSV();
        res += DELIMITER + wrapInQuotes(formatDateForStorage(start));
        res += DELIMITER + wrapInQuotes(formatDateForStorage(end));
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateForDisplay(start) +
                " to: " + formatDateForDisplay(end) + ")";
    }
}
