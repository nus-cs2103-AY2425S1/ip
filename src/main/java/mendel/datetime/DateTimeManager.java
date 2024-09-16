package mendel.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mendel.mendelexception.MendelException;

/**
 * The DateTimeManager class parses inputted of date-time strings to a consistent form.
 */
public class DateTimeManager {
    private static final String[] POSSIBLE_FORMATTED_TIME = new String[]{
        "dd-MMM-yyyy HH:mm", "dd/MMM/yyyy HH:mm", "dd MMM yyyy HH:mm",
        "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm",
        "d-M-yyyy HH:mm", "d/M/yyyy HH:mm", "dd-MMM-yyyy HHmm", "dd/MMM/yyyy HHmm", "dd MMM yyyy HHmm",
        "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm", "dd-MMM-yyyy HH mm", "dd/MMM/yyyy HH mm", "dd MMM yyyy HH mm",
        "d MMM yyyy HHmm", "dd-MM-yyyy HH mm", "dd/MM/yyyy HH mm", "d/M/yyyy HH mm", "d/M/yyyy HHmm",
        "d/MMM/yyyy HHmm", "d-M-yyyy HH mm", "d-M-yyyy HHmm", "d-MMM-yyyy HH mm", "d-MMM-yyyy HHmm",
        "d MMM yyyy HHmm", "MMM dd yyyy, HH mm", "MMM dd yyyy, HH:mm", "MMM dd yyyy, HHmm", "MMM dd yyyy HH:mm",
        "d/MMM/yyyy HH mm"
    };
    private static final String[] POSSIBLE_FORMATTED_UNTIME = new String[] {
        "dd-MMM-yyyy", "dd/MMM/yyyy", "dd MMM yyyy", "MMM dd yyyy", "MMM, dd yyyy",
        "d MMM yyyy", "d/MMM/yyyy", "d-MMM-yyyy", "MMM d yyyy", "MMM, d yyyy",
        "MM dd yyyy", "dd MM yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy dd MM", "yyyy, dd MM", "dd-MM-yyyy",
        "MM d yyyy", "d MM yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy d MM", "yyyy, d MM", "d-MM-yyyy",
        "M dd yyyy", "dd M yyyy", "dd/M/yyyy", "M/dd/yyyy", "yyyy dd M", "yyyy, dd M", "dd-M-yyyy",
        "M d yyyy", "d M yyyy", "d/M/yyyy", "M/d/yyyy", "yyyy d M", "yyyy, d M", "d-M-yyyy", "yyyy-MM-dd"
    };
    private final String rawDate;
    private String formattedDate;

    /**
     * Constructs a DateTimeManager object by attempting to parse a raw date string into a standard format.
     * If the provided date string cannot be parsed, it is stored as is.
     *
     * @param rawDate the input date string to be parsed and formatted
     */
    public DateTimeManager(String rawDate) throws MendelException {
        this.rawDate = rawDate;
        if (!this.isValidFormat()) {
            handleUnCompliantDate(rawDate);
        }
        this.formattedDate = parseDate();
    }
    private void handleUnCompliantDate(String rawDate) {
        try {
            LocalDateTime date = LocalDateTime.parse(rawDate);
            this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            String message = String.format(
                    "The date %s is not correctly formatted.\nWrite in form Month Day Year such as Aug 09 2024",
                    rawDate);
            throw new MendelException(message);
        }
    }

    /**
     * Checks if input datetime format is valid
     *
     * @return true if the date string matches a recognised format, false otherwise.
     */
    public boolean isValidFormat() {
        for (int i = 0; i < POSSIBLE_FORMATTED_TIME.length; i++) {
            if (this.isValidFormat(DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_TIME[i]))) {
                return true;
            }
        }

        for (int i = 0; i < POSSIBLE_FORMATTED_UNTIME.length; i++) {
            if (this.isValidFormat(DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_UNTIME[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given date string matches a specific date format.
     *
     * @param dateFormatter the regex representing the expected date format.
     * @return true if the date string matches the format, false otherwise.
     */
    private boolean isValidFormat(DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(rawDate, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String parseDate() {
        for (int i = 0; i < POSSIBLE_FORMATTED_TIME.length; i++) {
            if (this.isValidFormat(DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_TIME[i]))) {
                LocalDateTime date = LocalDateTime.parse(rawDate,
                        DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_TIME[i]));
                return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
            }
        }

        for (int i = 0; i < POSSIBLE_FORMATTED_UNTIME.length; i++) {
            if (this.isValidFormat(DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_UNTIME[i]))) {
                LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern(POSSIBLE_FORMATTED_UNTIME[i]));
                return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            }
        }
        return rawDate;
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
     * Converts DateTime to EpochTime seconds
     *
     * @return a long representing the Epoch time.
     */
    public long toEpochTime() {
        String formatDateNoTime = new DateTimeManager(this.formattedDate).removeTimeStamp();
        LocalDate date = LocalDate.parse(formatDateNoTime, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    public boolean isEarlierThan(DateTimeManager laterDate) {
        return this.toEpochTime() < laterDate.toEpochTime();
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
