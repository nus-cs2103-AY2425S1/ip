package utility;

import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd-MM-yyyy kkmm");
    public static final DateTimeFormatter PRETTY_DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
}
