package yapmeister.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event Task
 * @author BlazeChron
 */
public class Event extends Task {
    private final String from;
    private final String to;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    /**
     * Creates a Deadline Task
     * @param taskName Name of the task.
     * @param from Time of start.
     * @param to Time of end.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String exportString() {
        String completedString;
        if (this.getIsCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("E|%s|%s|%s|%s", completedString, this.getTaskName(), this.from, this.to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                                super.toString(),
                                this.from,
                                this.to);
    }
}
