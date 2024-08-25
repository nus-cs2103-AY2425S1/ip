import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDate getDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDate d = LocalDate.parse(date, inputFormatter);
        return d;
    }

    public static String formatOutputDate(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HHmm dd MMM yyyy");
        return date.format(outputFormatter);
    }
}
