import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static LocalDateTime InputToDateTime(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static String DateTimeToOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    public static LocalDateTime OutputToDateTime(String output) {
        return LocalDateTime.parse(output, DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }
}
