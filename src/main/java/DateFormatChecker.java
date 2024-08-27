import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormatChecker {
    private static final List<String> FORMATS = new ArrayList<>();

    static {
        FORMATS.add("yyyy-MM-dd HHmm");
        FORMATS.add("dd-MM-yyyy HHmm");
        FORMATS.add("d-MM-yyyy HHmm");
        FORMATS.add("MM-dd-yyyy HHmm");
        FORMATS.add("yyyy/MM/dd HHmm");
        FORMATS.add("dd/MM/yyyy HHmm");
        FORMATS.add("d/MM/yyyy HHmm");
        FORMATS.add("MM/dd/yyyy HHmm");
        FORMATS.add("MMM dd yyyy HHmm");
        FORMATS.add("MMM d yyyy HHmm");
    }

    public static String getDateFormat(String date) {
        for (String format : FORMATS) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                LocalDateTime.parse(date, formatter);
                return format;
            } catch (DateTimeException e) {
                // skip
            }
        }
        return "Unknown Format";
    }

    public static String getTimeFormat(String time) {
        String format = "HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalTime.parse(time, formatter);
            return format;
        } catch (DateTimeException e) {
            // skip
        }
        return "Unknown Format";
    }
}
