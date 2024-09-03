import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static DateTimeFormatter userInputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter storeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static LocalDateTime parseUserInputDate(String input) {
        return LocalDateTime.parse(input, userInputFormat);
    }

    public static LocalDateTime parseFileInputDate(String input) {
        return LocalDateTime.parse(input, storeFormat);
    }

    public static String toOutputString(LocalDateTime input) {
        return input.format(storeFormat);
    }
}
