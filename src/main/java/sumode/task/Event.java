package sumode.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class for tasks with specific timeframe.
 */
public class Event extends Task {

    private final String start;
    private final String end;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for Event
     *
     * @param name Name of task.
     * @param start Starting date of task.
     * @param end Ending date of task
     */
    public Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
        } catch (DateTimeParseException e) {
            startDate = null;
        }

        try {
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            endDate = null;
        }
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + (startDate == null ? this.start :this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + " to: "
                + (endDate == null ? this.end :this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    @Override
    public String savedString() {
        return "E | " + super.savedString() + " | " + this.start + " | " + this.end;
    }
}
