package duke.additionalParsers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
    );

    public String giveDate(String s){
    LocalDate date = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
            date = LocalDate.parse(s, formatter);
            return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
                } catch (DateTimeParseException e) {
            }
        }
        return null;
    }
}
