package chacha.parser;

import chacha.exception.WrongDateFormatException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateParser {
    public static LocalDate parseDate(String str) throws WrongDateFormatException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
