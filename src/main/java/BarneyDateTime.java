import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BarneyDateTime {
    private static final String DATE_TIME_RETURN_FORMAT = "yyyy-MM-dd";
    private static final String[] DATE_TIME_PARSE_FORMATS = {
            // Date Formats
            "yyyy-MM-dd", // 2024-08-21
            "dd/MM/yyyy", // 21/08/2024
            "MM-dd-yyyy", // 08-21-2024
            "yyyy/MM/dd", // 2024/08/21
            "yyyy.MM.dd", // 2024.08.21
            "dd-MMM-yyyy", // 21-Aug-2024
            "E, MMM dd yyyy", // Tue, Aug 21 2024
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", // 2024-08-21T14:30:00.000+0000
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", // 2024-08-21T14:30:00.000+00:00

            // Time Formats
            "HH:mm:ss", // 14:30:00
            "hh:mm:ss a", // 02:30:00 PM
            "HH:mm", // 14:30
            "hh:mm a", // 02:30 PM
            "HH:mm:ss.SSS", // 14:30:00.123
            "h:mm:ss a", // 2:30:00 PM

            // Date-Time Formats
            "yyyy-MM-dd HH:mm", // 2024-08-21 14:30
            "yyyy/MM/dd HH:mm", // 2024/08/21 14:30
            "dd MMM yyyy HH:mm", // 21 Aug 2024 14:30
            "yyyy-MM-dd HH:mm", // 2024-08-21 14:30
            "MM/dd/yyyy hh:mm a", // 08/21/2024 02:30 PM

            // Custom Formats
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", // ISO 8601 format
            "dd/MM/yyyy HH:mm:ss", // European format
            "MM/dd/yyyy hh:mm a", // US format
            "EEE, MMM d, yyyy h:mm a" // Short format with day of week
    };

    public static LocalDate parseDate(String dateString) {
        for (String format : DATE_TIME_PARSE_FORMATS) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_TIME_RETURN_FORMAT));
    }
}
