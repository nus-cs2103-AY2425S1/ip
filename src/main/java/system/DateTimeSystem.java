package system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeSystem {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");

    public LocalDateTime createDate(String y, String m, String d, String h, String min) {
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        int hour = Integer.parseInt(h);
        int minute = Integer.parseInt(min);

        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);

        return ldt;
    }

    public String format(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");

        return ldt.format(formatter);
    }
}
