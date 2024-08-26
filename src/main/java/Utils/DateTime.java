package Utils;

import Exceptions.AvoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    public static LocalDate parse(String input) throws AvoException {
        try {
            return LocalDate.parse(input.trim());
        } catch (DateTimeParseException e) {
            throw new AvoException("Please provide your date format in yyyy-mm-dd instead of " + input + "1");
        }
    }
    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
