package oyster.utils;

import java.time.LocalDateTime;

import oyster.exceptions.DateFormatException;

/**
 * Formats input and DateTime.
 */
public class DateTimeFormatter {
    /**
     * Creates a LocalDateTime based on input String to parse.
     *
     * @param input Line to be converted into LocalDateTime.
     * @return LocalDateTime.
     * @throws DateFormatException If parsing goes wrong.
     */
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
            if (date[i].length() == 1) {
                date[i] = "0" + date[i];
            }
        }

        try {
            return LocalDateTime.parse(
                    String.format("%s-%s-%sT%s:%s:%s", date[2], date[1], date[0], "00", "00", "00"));
        } catch (Exception e) {
            throw new DateFormatException();
        }
    }

    /**
     * Converts a LocalDateTime into a nice String.
     *
     * @param date Date to turn into nice String.
     * @return Formatted date string.
     */
    public static String format(LocalDateTime date) {
        return String.format("%s %s %s",
                date.getDayOfMonth(),
                date.getMonth().toString().substring(0, 3),
                date.getYear());
    }
}
