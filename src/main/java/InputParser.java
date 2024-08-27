import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class InputParser {

    public static String checkDateFormat(String time) {
        LocalDate date;
        LocalDateTime dateTime;
        // Match dates in the format "MM-DD-YYYY"
        String regex_m = "^(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD"
        String regex_y = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        // Match dates in the format "DD-MMM-YYYY"
        String regex_d = "^(0?[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD HH:MM"
        String regex_t1 = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]$";
        // Match dates in the format "YYYY-MM-DD HH:MM:SS"
        String regex_t2 = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]:[0-5][0-9]$";
        try {
            // Reformat as date only
            if (Pattern.matches(regex_m, time) || Pattern.matches(regex_y, time) || Pattern.matches(regex_d, time)) {
                date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return time;
            }

//          // Reformat as date and time only
            if (Pattern.matches(regex_t1, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                dateTime = LocalDateTime.parse(time, formatter);
                time = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
                return time;
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return time;
    }
}
