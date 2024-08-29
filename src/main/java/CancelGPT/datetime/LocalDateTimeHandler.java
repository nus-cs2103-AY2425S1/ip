package CancelGPT.datetime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeHandler {
    private LocalDateTime localDateTime;

    public LocalDateTimeHandler(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static LocalDateTimeHandler parseLocalDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return new LocalDateTimeHandler(LocalDateTime.parse(input, formatter));
    }

    public String getDisplayedLocalDateTime() throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return this.localDateTime.format(formatter);
    }

    public String getLocalDateTimeOriginal() throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.localDateTime.format(formatter);
    }
}
