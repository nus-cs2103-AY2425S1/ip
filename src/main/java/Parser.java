import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Parser {

    /**
     * Checks the length of the value given whether it is within the specified range.
     * Also checks if the value is parsable to an integer.
     */
    private static boolean dateCheck(String value, int min, int max, int minNo, int maxNo) {
        return value.length() >= min 
                && value.length() <= max 
                && value.chars().allMatch(Character::isDigit)
                && Integer.parseInt(value) >= minNo 
                && Integer.parseInt(value) <= maxNo;
    }

    public static String prependZero(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    public static String parseDateTimeString(LocalDateTime dateTime) {
        return prependZero(dateTime.getDayOfMonth())
                + "-" 
                + prependZero(dateTime.getMonthValue()) 
                + "-" + dateTime.getYear() 
                + " " + prependZero(dateTime.getHour()) 
                + prependZero(dateTime.getMinute());
    }

    public static String parseDateTimeStringAlt(LocalDateTime dateTime) {
        return Month.of(dateTime.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)
                + " " 
                + prependZero(dateTime.getDayOfMonth()) 
                + ", " + dateTime.getYear() 
                + ", " + prependZero(dateTime.getHour()) 
                + prependZero(dateTime.getMinute());
    }

    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeException {
        // Accepted Format: dd-mm-yyyy hhmm or dd/mm/yyyy hhmm, hhmm in 24-hour format
        String[] dateTimeBrokenDown = dateTime.replace("/", "-")
                .replace(" ", "-").split("-");
        if (dateTimeBrokenDown.length != 4
                || !dateCheck(dateTimeBrokenDown[0], 1, 2, 1, 31)
                || !dateCheck(dateTimeBrokenDown[1], 1, 2, 1, 12)
                || !dateCheck(dateTimeBrokenDown[2], 4, 4, 2024, 9999)
                || !dateCheck(dateTimeBrokenDown[3], 4, 4, 0, 2359)
                || !dateCheck(dateTimeBrokenDown[3].substring(0, 2), 2, 2, 0, 23)
                || !dateCheck(dateTimeBrokenDown[3].substring(2), 2, 2, 0, 59)) {;
            throw new DateTimeException("Invalid date time format");
        };
        return LocalDateTime.of(
                Integer.parseInt(dateTimeBrokenDown[2]),
                Integer.parseInt(dateTimeBrokenDown[1]),
                Integer.parseInt(dateTimeBrokenDown[0]),
                Integer.parseInt(dateTimeBrokenDown[3].substring(0, 2)),
                Integer.parseInt(dateTimeBrokenDown[3].substring(2))
        );
    }
}