import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final String beginString, endString;
    private boolean isDateBegin, isDateEnd;
    private LocalDate beginDate, endDate;

    public Event(String description, String begin, String end) {
        super(description);
        this.beginString = begin;
        this.endString = end;
        isDateBegin = isDateEnd = false;
        try {
            beginDate = CustomDateTimeParser.parseDateTime(begin);
            isDateBegin = true;
        } catch (DateTimeParseException ignored) {
        }
        try {
            endDate = CustomDateTimeParser.parseDateTime(end);
            isDateEnd = true;
        } catch (DateTimeParseException ignored) {
        }
    }

    /**
     * Returns the start of the event.
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
        String prev = super.toString();
        return "[E" + prev.substring(2) + " (begins: " + getBegin() + ", ends: " + getEnd() + ")";
    }
}
