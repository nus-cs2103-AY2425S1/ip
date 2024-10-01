package lict;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code lict.DateTime} class handles date and time information, allowing for flexible input of either date only
 * or both date and time.
 * It provides methods to format the date and time for display and data storage purposes.
 */
public class DateTime {
    protected static final String WHITESPACE_DELIMITER = "\\s+";
    protected DateTimeFormatter stringFormatter;
    protected DateTimeFormatter dataFormatter;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    /**
     * Constructs a {@code lict.DateTime} object by parsing the provided string, which can be in
     * the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
     * Depending on the format, it initializes either a {@code LocalDate} or a {@code LocalDateTime}.
     *
     * @param info The date or date and time information as a string.
     * @throws DateTimeException if the input string does not match the expected formats.
     */
    public DateTime(String info) throws DateTimeException {
        String[] details = info.split(WHITESPACE_DELIMITER, 2);
        DateTimeFormatter formatter;
        if (details.length == 2) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
            this.dateTime = LocalDateTime.parse(info.trim(), formatter);
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            this.date = LocalDate.parse(info.trim(), formatter);
        }
        this.dataFormatter = formatter;
    }

    /**
     * Returns the formatted string representation of the date or date-time.
     * The format used depends on whether the object was initialized with a date only or both date and time.
     *
     * @return A string representing the formatted date or date-time.
     */
    public String getString() {
        if (this.date != null) {
            return this.date.format(this.stringFormatter);
        } else {
            return this.dateTime.format(this.stringFormatter);
        }
    }

    /**
     * Returns the formatted string suitable for data storage, representing the date or date-time.
     * The format will match the input format used to initialize the object.
     *
     * @return A string formatted for data storage representing the date or date-time.
     */
    public String getData() {
        if (this.date != null) {
            return this.date.format(this.dataFormatter);
        } else {
            return this.dateTime.format(this.dataFormatter);
        }
    }
}
