package pixel;

import java.util.List;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class DateTimeParser {
    private LocalDate dateTime;

    public DateTimeParser(String input) throws PixelException {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.dateTime = parseDate(input.strip(), formatters);
    }

    private static LocalDate parseDate(String dateTimeString, List<DateTimeFormatter> formatters)
            throws PixelException {
        Pattern ddMMyyyyPattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Pattern yyyyMMddPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern MMddyyyyPattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

        if (ddMMyyyyPattern.matcher(dateTimeString).matches()) {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else if (yyyyMMddPattern.matcher(dateTimeString).matches()) {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (MMddyyyyPattern.matcher(dateTimeString).matches()) {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }

        throw new PixelException(
                (String.format("Date-time %s could not be parsed, follow a format like this: dd-MM-yyyy",
                        dateTimeString)));
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
