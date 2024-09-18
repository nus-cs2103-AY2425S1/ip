package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private static final String displayDateFormat = "MMM d yyyy";
    private static final String storageDateFormat = "yyyy-MM-dd";

    private LocalDate fromDate;

    private LocalDate toDate;

    /**
     * Constructs an Event task.
     *
     * @param description Task Description.
     * @param fromDate Date to do task from.
     * @param toDate Date to do task until.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns task in storage format.
     */
    @Override
    public String toStorageString() {
        String formattedFromDate = this.fromDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        String formattedToDate = this.toDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        return String.format("E | %s | %s | %s", super.toStorageString(), formattedFromDate, formattedToDate);
    }

    /**
     * Returns task in display format.
     */
    @Override
    public String toString() {
        String formattedFromDate = this.fromDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        String formattedToDate = this.toDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        return String.format("[E] %s (from: %s to: %s)", super.toString(), formattedFromDate, formattedToDate);
    }
}
