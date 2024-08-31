import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

}
