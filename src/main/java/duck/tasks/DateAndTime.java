package duck.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class representing date and time.
 */
public class DateAndTime {
    private LocalDate date;
    private String str;

    /**
     * Constructor for DateAndTime.
     *
     * @param str String representing date and time in "yyyy-MM-dd" format.
     * @throws DateTimeParseException
     */
    public DateAndTime(String str) throws DateTimeParseException {
        this.str = str;
        this.date = LocalDate.parse(str);
    }

    /**
     * Gets the date represented by this object.
     *
     * @return The date represented by this object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the original string passed into this object.
     *
     * @return The original string passed into this object.
     */
    public String getOriginalString() {
        return str;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", date.getDayOfMonth(), date.getMonth(), date.getYear());
    }
}
