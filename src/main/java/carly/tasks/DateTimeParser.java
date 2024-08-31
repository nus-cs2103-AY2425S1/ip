package carly.tasks;

import carly.exception.CarlyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/** A utility class for parsing and formatting dates. */
public class DateTimeParser {

    /** The date string to be parsed and formatted. */
    private final String date;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public DateTimeParser(String date) {
        this.date = date;
    }

    /** Formats the date string to "MMM dd yyyy" format. */
    public String formatDateTime() {
        try {
            LocalDate parsedDate = LocalDate.parse(this.date, INPUT_FORMATTER);
            return parsedDate.format(OUTPUT_FORMATTER);
        } catch (Exception e) {
            System.out.println("Invalid date format: " + this.date);
            return null;
        }
    }

    /** Parses the date string to a {@link LocalDate} object. */
    public LocalDate getLocalDate() throws CarlyException {
        try {
            String formattedDate = this.formatDateTime();
            return LocalDate.parse(formattedDate, OUTPUT_FORMATTER);
        } catch (Exception e) {
            throw new CarlyException("Error parsing the formatted date. Please ensure input is in \"yyyy-MM-dd\" "
                    + "format. and represents a valid date");
        }

    }
}
