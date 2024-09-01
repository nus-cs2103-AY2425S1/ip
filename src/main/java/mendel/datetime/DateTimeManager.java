package mendel.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateTimeManager class parses inputted of date-time strings to a consistent form.
 */
public class DateTimeManager {
    private String formattedDate;

    /**
     * Constructs a DateTimeManager object by attempting to parse a raw date string into a standard format.
     * If the provided date string cannot be parsed, it is stored as is.
     *
     * @param rawDate the input date string to be parsed and formatted.
     */
    public DateTimeManager(String rawDate) {
        try {
            LocalDateTime date = LocalDateTime.parse(rawDate);
            this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            boolean hasValidFormat = false;
            String[] possibleFormatsTimed = new String[] {
                "dd-MMM-yyyy HH:mm", "dd/MMM/yyyy HH:mm", "dd MMM yyyy HH:mm",
                "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm",
                "d-M-yyyy HH:mm", "d/M/yyyy HH:mm", "dd-MMM-yyyy HHmm", "dd/MMM/yyyy HHmm", "dd MMM yyyy HHmm",
                "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm", "dd-MMM-yyyy HH mm", "dd/MMM/yyyy HH mm", "dd MMM yyyy HH mm",
                "dd-MM-yyyy HH mm", "dd/MM/yyyy HH mm", "d/M/yyyy HH mm", "d/M/yyyy HHmm", "d/MMM/yyyy HH mm",
                "d/MMM/yyyy HHmm", "d-M-yyyy HH mm", "d-M-yyyy HHmm", "d-MMM-yyyy HH mm", "d-MMM-yyyy HHmm",
                "d MMM yyyy HHmm"
            };
            for (int i = 0; i < possibleFormatsTimed.length; i++) {
                if (this.isValidFormat(rawDate, DateTimeFormatter.ofPattern(possibleFormatsTimed[i]))) {
                    LocalDateTime date = LocalDateTime.parse(rawDate,
                            DateTimeFormatter.ofPattern(possibleFormatsTimed[i]));
                    this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
                    hasValidFormat = true;
                    break;
                }
            }
            String[] possibleFormatsUnTimed = new String[] {
                "dd-MMM-yyyy", "dd/MMM/yyyy", "dd MMM yyyy", "MMM dd yyyy", "MMM, dd yyyy",
                "d MMM yyyy", "d/MMM/yyyy", "d-MMM-yyyy", "MMM d yyyy", "MMM, d yyyy",
                "MM dd yyyy", "dd MM yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy dd MM", "yyyy, dd MM", "dd-MM-yyyy",
                "MM d yyyy", "d MM yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy d MM", "yyyy, d MM", "d-MM-yyyy",
                "M dd yyyy", "dd M yyyy", "dd/M/yyyy", "M/dd/yyyy", "yyyy dd M", "yyyy, dd M", "dd-M-yyyy",
                "M d yyyy", "d M yyyy", "d/M/yyyy", "M/d/yyyy", "yyyy d M", "yyyy, d M", "d-M-yyyy"
            };
            for (int i = 0; i < possibleFormatsUnTimed.length; i++) {
                if (this.isValidFormat(rawDate, DateTimeFormatter.ofPattern(possibleFormatsUnTimed[i]))) {
                    LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern(possibleFormatsUnTimed[i]));
                    this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    hasValidFormat = true;
                    break;
                }
            }
            if (!hasValidFormat) {
                this.formattedDate = rawDate;
            }
        }
    }

    /**
     * Removes the time component from the formatted date string, if it exists.
     *
     * @return a string with date and without time.
     */
    public String removeTimeStamp() {
        try {
            LocalDate date = LocalDate.parse(this.formattedDate, DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return this.formattedDate;
        }
    }
    /**
     * Checks if a given date string matches a specific date format.
     *
     * @param rawDate the date string to validate.
     * @param dateFormatter the regex representing the expected date format.
     * @return true if the date string matches the format, false otherwise.
     */
    private boolean isValidFormat(String rawDate, DateTimeFormatter dateFormatter) {
        try {
            LocalDate date = LocalDate.parse(rawDate, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    /**
     * Returns the formatted date string.
     *
     * @return a string representing the formatted date.
     */
    @Override
    public String toString() {
        return this.formattedDate;
    }
}
