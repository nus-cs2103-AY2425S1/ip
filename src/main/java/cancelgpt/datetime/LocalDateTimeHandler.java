package cancelgpt.datetime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a local date time handler,
 * which converts a local date time in
 * yyyy-MM-dd HHmm to MMM dd yyyy HHmm to display,
 * and parses local date time of MMM dd yyyy HHmm to
 * a LocalDateTimeHandler object.
 */
public class LocalDateTimeHandler {
    private final LocalDateTime localDateTime;

    /**
     * Initialises the local date time and sets
     * its local date time equal to the given LocalDateTime object
     *
     * @param localDateTime the local date time to set to
     */
    public LocalDateTimeHandler(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Returns a LocalDateTimeHandler by parsing the input local date time string
     * in yyyy-MM-dd HHmm format.
     *
     * @param input the string representation of the local date time
     * @return LocalDateTimeHandler object with the parsed local date time string
     * @throws DateTimeParseException if the input date cannot be parsed
     */
    public static LocalDateTimeHandler parseLocalDateTimeStringToHandler(String input)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return new LocalDateTimeHandler(LocalDateTime.parse(input, formatter));
    }

    /**
     * Returns the string representation of the
     * local date time in MMM dd yyyy HHmm format.
     *
     * @return the string representation of local date time in MMM dd yyyy HHmm
     * @throws DateTimeException if the local date time cannot be formatted properly
     */
    public String getDisplayedLocalDateTime() throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return this.localDateTime.format(formatter);
    }

    /**
     * Returns the string representation of the local date time in its original
     * yyyy-MM-dd HHmm format.
     *
     * @return the string representation of the local date time in its original
     *     yyyy-MM-dd HHmm format
     * @throws DateTimeException if the local date time cannot be formatted properly
     */
    public String getLocalDateTimeOriginal() throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.localDateTime.format(formatter);
    }
}
