package vecrosen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class containing static function to parse dates.
 */
public class CustomDateTimeParser {
    private static final String[] supportedDates = {
            "uuuu-MM-dd", "uu-MM-dd",
            "dd MMM, uuuu", "MMM dd, uuuu", "dd MMM uuuu", "MMM dd uuuu", "uuuu MMM dd", "uuuu dd MMM",
            "dd MMMM, uuuu", "MMMM dd, uuuu", "dd MMMM uuuu", "MMMM dd uuuu", "uuuu MMMM dd", "uuuu dd MMMM",
            "MM/dd/uuuu", "MM/dd/uu", "MM/dd",
            "MMM dd", "dd MMM",
            "MMMM dd", "dd MMMM"
    }; //TODO shorten this using []

    /**
     * Parses the string into a date, if it is recognizable as one.
     *
     * @param by The string to be parsed
     * @return The date detected
     * @throws DateTimeParseException Thrown if no matches found
     */
    public static LocalDate parseDateTime(String by) throws DateTimeParseException {
        for (int i = 0; i < supportedDates.length; ++i) {
            try {
                LocalDate dt = LocalDate.parse(by,
                        DateTimeFormatter.ofPattern(supportedDates[i]));
                return dt;
            } catch (DateTimeParseException ignored) {
                // no action
            }
        }
        throw new DateTimeParseException("No match found!", by, 0);
    }
}
