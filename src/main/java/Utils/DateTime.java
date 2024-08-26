package Utils;

import Exceptions.AvoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    public static LocalDate parse(String date) throws AvoException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new AvoException("Please provide your date format in yyyy-mm-dd");
        }
    }
    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
