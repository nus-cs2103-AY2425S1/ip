import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {

    public static LocalDateTime convertToDateTime(String input) {
        String[] parts = input.split(" ");

        // Parse the date part
        String[] dateParts = parts[0].split("/");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time;

        // Parse the time part
        if (parts.length == 2) {
            String timePart = parts[1];
            int hour = Integer.parseInt(timePart.substring(0, 2));
            int minute = Integer.parseInt(timePart.substring(2, 4));
            time = LocalTime.of(hour, minute);
        } else {
                time = LocalTime.MIDNIGHT;
        }
        return LocalDateTime.of(date, time);
    }

    public static String formatDateTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return time.format(formatter);
    }
}
