package evan.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an instance in time where a Task can start or end.
 * This class uses the factory method design pattern.
 */
public class DateTime {
    private final String textValue;
    private final LocalDate dateValue;
    private final LocalDateTime dateTimeValue;
    final InputType type;

    /**
     * Instantiates a DateTime object.
     * This is a private constructor as the DateTime class uses the factory method design pattern.
     *
     * @param textValue     String that describes the instance in time.
     * @param dateValue     LocalDate that represents the instance in time.
     * @param dateTimeValue LocalDateTime that represents the instance in time.
     * @param type          Type of this DateTime object.
     */
    private DateTime(String textValue, LocalDate dateValue, LocalDateTime dateTimeValue, InputType type) {
        this.textValue = textValue;
        this.dateValue = dateValue;
        this.dateTimeValue = dateTimeValue;
        this.type = type;
    }

    /**
     * Creates a DateTime object that uses a STRING.
     *
     * @param text String that represents the instance in time.
     * @return DateTime object that uses the STRING type.
     */
    public static DateTime of(String text) {
        return new DateTime(text, null, null, InputType.STRING);
    }

    /**
     * Creates a DateTime object that uses a DATE.
     *
     * @param date LocalDate that represents the instance in time.
     * @return DateTime object that uses the DATE type.
     */
    public static DateTime of(LocalDate date) {
        return new DateTime(null, date, null, InputType.DATE);
    }

    /**
     * Creates a DateTime object that uses a DATETIME.
     *
     * @param dateTime LocalDateTime that represents the instance in time.
     * @return DateTime object that uses the DATETIME type
     */
    public static DateTime of(LocalDateTime dateTime) {
        return new DateTime(null, null, dateTime, InputType.DATETIME);
    }

    /**
     * Parses a String and determines which of() method to call to create the appropriate DateTime object.
     * If the input is in 'yyyy-MM-dd' format, a DateTime object with a DATE type will be created.
     * If the input is in 'yyyy-MM-dd HHmm' format, a DateTime object with a DATETIME type wille be created.
     * If the input is not in a valid date and/or time format, a DateTime object with a STRING type will be created.
     *
     * @param input String that represents an instance of time.
     * @return DateTime object.
     */
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

    /**
     * Checks if the DateTime object is using a STRING.
     *
     * @return True if the DateTime object is using a STRING, and false otherwise.
     */
    public boolean isString() {
        return type == InputType.STRING;
    }

    /**
     * Checks if the DateTime object is using a DATE.
     *
     * @return True is the DateTime object is using a DATE, and false otherwise.
     */
    public boolean isDate() {
        return type == InputType.DATE;
    }

    /**
     * Checks if the DateTime object is using a DATETIME.
     *
     * @return True if the DateTime object is using a DATETIME, and false otherwise.
     */
    public boolean isDateTime() {
        return type == InputType.DATETIME;
    }

    // Method to get the string value (if it's a string)

    /**
     * Returns the String value if the DateTime object is using a STRING type.
     */
    public String getTextValue() {
        if (!isString()) {
            throw new IllegalStateException("Not a text value");
        }
        return textValue;
    }

    /**
     * Returns the LocalDate value if the DateTime object is using a DATE type.
     */
    public LocalDate getDateValue() {
        if (!isDate()) {
            throw new IllegalStateException("Not a date value");
        }
        return dateValue;
    }

    /**
     * Returns the LocalDateTime value if the DateTime object is using a DATETIME type.
     */
    public LocalDateTime getDateTimeValue() {
        if (!isDateTime()) {
            throw new IllegalStateException("Not a datetime value");
        }
        return dateTimeValue;
    }

    /**
     * Returns a String representation of the DateTime object.
     * If the DateTime object is using a STRING, the normal string is returned with no additional formatting.
     * If the DateTime object is using a DATE, a string with 'dd MMM yyyy' format is returned.
     * If the DateTime object is using a DATETIME, a string with 'dd MMM yyyy, hh:mm a' format is returned
     */
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

    /**
     * Represents the three types that a DateTime object can use to represent the instance in time.
     */
    public enum InputType {
        STRING, DATE, DATETIME
    }
}
