package yapmeister.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final String from;
    private final String to;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    public Event(String taskName, String from, String to) {
        super(taskName);
        String f;
        LocalDate fD = null;
        try {
            fD = LocalDate.parse(from);
            f = fD.format(DateTimeFormatter.ISO_WEEK_DATE);
        } catch (DateTimeParseException e) {
            f = from;
        }
        this.fromDate = fD;
        this.from = f;
        String t;
        LocalDate tD = null;
        try {
            tD = LocalDate.parse(to);
            t = tD.format(DateTimeFormatter.ISO_WEEK_DATE);
        } catch (DateTimeParseException e) {
            t = to;
        }
        this.toDate = tD;
        this.to = t;
    }
    @Override
    public String exportString() {
        String completedString;
        if (this.isCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("E|%s|%s|%s|%s", completedString, this.getTaskName(), this.from, this.to);
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                                super.toString(),
                                this.from,
                                this.to);
    }
}
