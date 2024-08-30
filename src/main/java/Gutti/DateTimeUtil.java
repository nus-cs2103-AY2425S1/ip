package Gutti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    public static final DateTimeFormatter FORMAT1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter FORMAT2 = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    public static final DateTimeFormatter FORMAT3 = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    public static final DateTimeFormatter FORMAT4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        // Try each formatter until one succeeds
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT1);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        } try {
            return LocalDateTime.parse(dateTimeString, FORMAT2);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        } try {
            return LocalDateTime.parse(dateTimeString, FORMAT3);
        } catch (DateTimeParseException e) {
            // Continue to next formatter
        }
        try {
            return LocalDateTime.parse(dateTimeString, FORMAT4);
        } catch (DateTimeParseException e) {
            // All formatters failed
            throw new DateTimeParseException("Invalid date and time format", dateTimeString, 0);
        }
    }
}