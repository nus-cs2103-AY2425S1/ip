package pixel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * The DateTimeParser class is responsible for parsing date-time strings into
 * LocalDate objects.
 */
public class DateTimeParser {
    private LocalDate dateTime;

    /**
     * Constructs a DateTimeParser object with the given input string.
     *
     * @param input the date-time string to be parsed
     * @throws PixelException if the input string cannot be parsed into a valid
     *                        date-time format
     */
    public DateTimeParser(String input) throws PixelException {
        HashMap<Pattern, DateTimeFormatter> formatters = new HashMap<>();

        formatters.put(Pattern.compile("\\d{2}-\\d{2}-\\d{4}"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formatters.put(Pattern.compile("\\d{4}-\\d{2}-\\d{2}"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.put(Pattern.compile("\\d{2}/\\d{2}/\\d{4}"), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        formatters.put(Pattern.compile("\\b\\d{2} [A-Za-z]{3} \\d{4}\\b"), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        this.dateTime = parseDate(input.strip(), formatters);
        assert this.dateTime != null : "Parsed dateTime should not be null";
    }

    /**
     * Parses the given date-time string using the provided list of formatters.
     *
     * @param dateTimeString the date-time string to be parsed
     * @param formatters     the list of formatters to be used for parsing
     * @return the parsed LocalDate object
     * @throws PixelException if the date-time string cannot be parsed using any of
     *                        the formatters
     */
    private static LocalDate parseDate(String dateTimeString, HashMap<Pattern, DateTimeFormatter> formatters)
            throws PixelException {

        for (Map.Entry<Pattern, DateTimeFormatter> entry : formatters.entrySet()) {
            Pattern pattern = entry.getKey();
            DateTimeFormatter formatter = entry.getValue();
            if (pattern.matcher(dateTimeString).matches()) {
                return LocalDate.parse(dateTimeString, formatter);
            }
        }

        throw new PixelException((String.format(
                "Date-time %s could not be parsed, follow a format like this: dd-MM-yyyy", dateTimeString)));
    }

    /**
     * Returns a string representation of the parsed date in the format "dd MMM
     * yyyy".
     *
     * @return the formatted date string
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public LocalDate getDateTime() {
        return dateTime;
    }
}
