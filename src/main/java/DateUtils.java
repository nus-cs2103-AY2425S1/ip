import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String USER_INPUT_FORMAT_STRING = "yyyy-MM-dd HH:mm";
    public static final String STORE_FORMAT_STRING = "dd-MM-yyyy HH:mm";
    private static final DateTimeFormatter USER_INPUT_FORMATTER = DateTimeFormatter.ofPattern(USER_INPUT_FORMAT_STRING);
    private static final DateTimeFormatter STORE_FORMATTER = DateTimeFormatter.ofPattern(STORE_FORMAT_STRING);
    public static LocalDateTime parseUserInputDate(String input) {
        return LocalDateTime.parse(input, USER_INPUT_FORMATTER);
    }

    public static LocalDateTime parseFileInputDate(String input) {
        return LocalDateTime.parse(input, STORE_FORMATTER);
    }

    public static String toOutputString(LocalDateTime input) {
        return input.format(STORE_FORMATTER);
    }
}
