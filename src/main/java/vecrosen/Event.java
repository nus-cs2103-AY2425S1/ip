package vecrosen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with 2 additional arguments: a begin and end time/date.
 */
public class Event extends Task {
    private final String beginString;
    private final String endString;
    private boolean isDateBegin;
    private boolean isDateEnd;
    private LocalDate beginDate;
    private LocalDate endDate;

    /**
     * Initializes a new Event object. Starts marked as incomplete.
     *
     * @param description The description of the event
     * @param begin The start date/time of the event
     * @param end The end date/time of the event
     */
    public Event(String description, String begin, String end) {
        super(description);
        this.beginString = begin;
        this.endString = end;
        isDateBegin = isDateEnd = false;
        try {
            beginDate = CustomDateTimeParser.parseDateTime(begin);
            isDateBegin = true;
        } catch (DateTimeParseException ignored) {
            // no action
        }
        try {
            endDate = CustomDateTimeParser.parseDateTime(end);
            isDateEnd = true;
        } catch (DateTimeParseException ignored) {
            // no action
        }
    }

    /**
     * Returns the start of the event.
     *
     * @return The start of the event
     */
    public String getBegin() {
        if (isDateBegin) {
            return beginDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } else {
            return beginString;
        }
    }

    /**
     * Returns the end of the event.
     *
     * @return The end of the event
     */
    public String getEnd() {
        if (isDateEnd) {
            return endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } else {
            return endString;
        }
    }

    @Override
    public String toString() {
        String taskDesc = super.toString();
        return "[E" + taskDesc.substring(2) + " (begins: " + getBegin() + ", ends: " + getEnd() + ")";
    }
}
