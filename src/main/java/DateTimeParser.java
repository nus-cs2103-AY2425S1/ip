import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public static LocalDate parseDate(String dateString) {
        for (String format : Constants.DATE_TIME_PARSE_FORMATS) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }
}
