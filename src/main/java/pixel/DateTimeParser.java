package pixel;

import java.util.List;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class DateTimeParser {
    private LocalDate dateTime;

    public DateTimeParser(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.dateTime = parseDate(input.strip(), formatters);
    }

    private static LocalDate parseDate(String dateTimeString, List<DateTimeFormatter> formatters)
            throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException(
                (String.format("Date-time %s could not be parsed, follow a format like this: dd-MM-yyyy",
                        dateTimeString)),
                dateTimeString, 0);
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
