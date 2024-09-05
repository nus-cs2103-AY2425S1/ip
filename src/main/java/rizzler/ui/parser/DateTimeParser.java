package rizzler.ui.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public static LocalDate to_datetime(String inputStr) throws DateTimeParseException {
        // String[] splitInput = input_str.split(" ");
        LocalDate date = LocalDate.parse(inputStr);
        return date;
    }

    public static String to_str(LocalDate inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return inputDate.format(formatter);
    }
}
