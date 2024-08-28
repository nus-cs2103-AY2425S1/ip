package oyster.utils;

import oyster.exceptions.DateFormatException;

import java.time.LocalDateTime;

public class DateTimeFormatter {
    public static LocalDateTime readInput(String input) throws DateFormatException {
        String[] date = input.split("/");

        // Check date values
        if (date.length != 3) {
            throw new DateFormatException();
        }

        for (int i = 0; i < date.length; i++) {
            date[i] = date[i].trim();
        }

        // Check is numeric
        try {
            // Day
            Integer.parseInt(date[0]);
            if (date[0].length() > 2) {
                throw new DateFormatException();
            }

            // Month
            Integer.parseInt(date[1]);
            if (date[1].length() > 2) {
                throw new DateFormatException();
            }

            // Year
            Integer.parseInt(date[2]);
            if (date[2].length() != 4) {
                throw new DateFormatException();
            }
        } catch (NumberFormatException e) {
            throw new DateFormatException();
        }

        for (int i = 0; i < date.length; i++) {
            if (i > 0 && date[i].length() == 1) {
                date[i] = "0" + date[i];
            }
        }

        return LocalDateTime.parse(
                String.format("%s-%s-%sT%s:%s:%s", date[2], date[1], date[0], "00", "00", "00"));
    }

    public static String format(LocalDateTime date) {
        return String.format("%s %s %s",
                date.getDayOfMonth(),
                date.getMonth().toString().substring(0,3),
                date.getYear());
//        return String.format("%s:%s %s %s %s",
//                date.getHour(),
//                date.getMinute(),
//                date.getDayOfWeek(),
//                date.getMonth(),
//                date.getYear());
    }
}
