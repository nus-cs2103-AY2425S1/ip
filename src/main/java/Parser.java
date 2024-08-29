import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static String[] tokenizeAdd(String command) {
        String[] strToken = command.split(" /");
        return strToken;
    }

    public LocalDateTime parseDate(String date) {

        LocalDateTime formatted = LocalDateTime.parse(date, formatter);
        return formatted;
    }

    public String dateToString(LocalDateTime date) {
        return date.format(formatter);
    }
}
