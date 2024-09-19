package utility;

import java.time.format.DateTimeFormatter;

/**
 * This class provides the specific date time pattern for either printing or regex.
 */
public class CustomDateTimeFormatter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy kkmm");
    public static final DateTimeFormatter DATE_TIME_FORMATTER_PRETTY =
            DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
}
