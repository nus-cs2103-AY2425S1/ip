package chacha.parser;

import chacha.exception.WrongTimeFormatException;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    public static LocalTime parseStringToTime(String str) {
        try {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[.mm]a");
            return LocalTime.parse(str, parser);
        } catch (DateTimeException e) {
            return null;
        }
    }
    public static String parseTimeToString(LocalTime time) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[.mm]a");
        return time.format(parser).toUpperCase();
    }
}
