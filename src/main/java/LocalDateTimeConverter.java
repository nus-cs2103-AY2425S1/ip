import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeConverter {

    public static LocalDateTime getLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static String getDifferentFormat(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HHmm");
        return ldt.format(formatter);
    }

}
