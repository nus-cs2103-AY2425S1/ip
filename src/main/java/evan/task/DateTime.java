package evan.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private final String textValue;
    private final LocalDate dateValue;
    private final LocalDateTime dateTimeValue;
    final InputType type;

    // Private constructor to initialize DateTime
    private DateTime(String textValue, LocalDate dateValue, LocalDateTime dateTimeValue, InputType type) {
        this.textValue = textValue;
        this.dateValue = dateValue;
        this.dateTimeValue = dateTimeValue;
        this.type = type;
    }

    // Static factory method for creating a DateTime with String
    public static DateTime of(String text) {
        return new DateTime(text, null, null, InputType.STRING);
    }

    // Static factory method for creating a DateTime with LocalDate
    public static DateTime of(LocalDate date) {
        return new DateTime(null, date, null, InputType.DATE);
    }

    // Static factory method for creating a DateTime with LocalDateTime
    public static DateTime of(LocalDateTime dateTime) {
        return new DateTime(null, null, dateTime, InputType.DATETIME);
    }

    public static DateTime parseInput(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            // Attempt to parse the input as LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(input, dateTimeFormatter);
            return DateTime.of(dateTime);
        } catch (DateTimeParseException e1) {
            // If parsing as LocalDateTime fails, try parsing as a LocalDate
            try {
                LocalDate date = LocalDate.parse(input, dateFormatter);
                return DateTime.of(date);
            } catch (DateTimeParseException e2) {
                // If parsing as LocalDate fails, return the input as a String
                return DateTime.of(input);
            }
        }
    }

    // Method to check if the input is a string
    public boolean isString() {
        return type == InputType.STRING;
    }

    // Method to check if the input is a date without time
    public boolean isDate() {
        return type == InputType.DATE;
    }

    // Method to check if the input is a date with time
    public boolean isDateTime() {
        return type == InputType.DATETIME;
    }

    // Method to get the string value (if it's a string)
    public String getTextValue() {
        if (!isString()) {
            throw new IllegalStateException("Not a text value");
        }
        return textValue;
    }

    // Method to get the date value (if it's a date without time)
    public LocalDate getDateValue() {
        if (!isDate()) {
            throw new IllegalStateException("Not a date value");
        }
        return dateValue;
    }

    // Method to get the datetime value (if it's a date with time)
    public LocalDateTime getDateTimeValue() {
        if (!isDateTime()) {
            throw new IllegalStateException("Not a datetime value");
        }
        return dateTimeValue;
    }

    @Override
    public String toString() {
        // Set the format that will be displayed
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

        return switch (type) {
            case STRING -> textValue;
            case DATE -> dateValue.format(dateFormatter);
            case DATETIME -> dateTimeValue.format(dateTimeFormatter);
        };
    }

    public enum InputType {
        STRING, DATE, DATETIME
    }
}
