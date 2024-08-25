package utility;

import static ui.Ui.printDivider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtility {
    private static final DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
    };

    private static final DateTimeFormatter[] dateFormatters = {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static LocalDateTime parse(String s) {
        // Try parsing as LocalDateTime
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        // Try parsing as LocalDate if LocalDateTime parsing fails
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(s, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }

        // If all parsing attempts fail, print an error message and return null
        System.out.println("Invalid datetime format: " + s);
        printDivider();
        return null;
    }

    public static String format(LocalDateTime date) {
        return date.format(outputFormatter);
    }
}
