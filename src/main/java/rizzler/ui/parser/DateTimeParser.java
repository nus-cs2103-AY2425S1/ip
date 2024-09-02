package rizzler.ui.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public static LocalDate to_datetime(String input_str) throws DateTimeParseException {
        // String[] splitInput = input_str.split(" ");
        LocalDate date = LocalDate.parse(input_str);
        return date;
    }

    public static String to_str(LocalDate input_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return input_date.format(formatter);
    }
}
