package socchat;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static String[] tokenizeAdd(String command) {
        String[] strToken = command.split(" /");
        return strToken;
    }

    public static LocalDateTime parseDate(String date) throws SocchatException {
        try {
            LocalDateTime formatted = LocalDateTime.parse(date, FORMATTER);
            return formatted;
        } catch (DateTimeException e) {
            throw new SocchatException("Please enter your dateTime as this format --- yyyy-MM-dd HH:mm");
        }

    }

    public static String dateToString(LocalDateTime date) {
        return date.format(FORMATTER);
    }
}
