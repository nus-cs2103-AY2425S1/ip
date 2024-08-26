package parser;

import exception.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser to extract date and time information from provided information.
 */
public class DateParser {

    private static String parseDateTimeGeneral(String pattern, String target) throws ParseException {
        Pattern byPattern = Pattern.compile(pattern);
        Matcher byMatcher = byPattern.matcher(target);

        if (byMatcher.find()) {
            if (byMatcher.group(2) != null) {
                return byMatcher.group(1) + "T" + byMatcher.group(2);
            } else {
                return byMatcher.group(1) + "T00:00";
            }
        } else {
            throw new ParseException("Wrong format of dates");
        }
    }

    /**
     * Parse the description for deadline and return the extracted dates and time.
     *
     * @param eventString input of a description of deadline
     * @return a string represents the date and time of the deadline information
     * @throws ParseException when string cannot be parsed
     */
    public static String parseBy(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/by\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     * Parse the description of event and return the extracted starting dates and times.
     *
     * @param eventString a description of event
     * @return a string represents starting date and time of the event information
     * @throws ParseException when string cannot be parsed
     */
    public static String parseFrom(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     * Parse the description of event and return the extracted ending dates and times.
     *
     * @param eventString a description of event
     * @return a string represents ending date and time of the event information
     * @throws ParseException when string cannot be parsed
     */
    public static String parseTo(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/to\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static String parseRecordedDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.toString();
    }
}
