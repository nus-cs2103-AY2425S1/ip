package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.ParseException;

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
     * Parses the date information after the keyword by.
     *
     * @param eventString input from user.
     * @return the string of date after keyword by.
     * @throws ParseException when fails to find a valid date.
     */
    public static String parseBy(String eventString) throws ParseException {
        assert !eventString.isEmpty();
        return parseDateTimeGeneral(
                "/by\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     * Parses the date information after the keyword from.
     *
     * @param eventString input from user.
     * @return the string of date.
     * @throws ParseException the date is in invalid format.
     */
    public static String parseFrom(String eventString) throws ParseException {
        assert !eventString.isEmpty();
        return parseDateTimeGeneral(
                "/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     * Parses the date information after the keyword to.
     *
     * @param eventString input from user.
     * @return the string of date.
     * @throws ParseException the date is in invalid format.
     */
    public static String parseTo(String eventString) throws ParseException {
        assert !eventString.isEmpty();
        return parseDateTimeGeneral(
                "/to\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    /**
     * Converts a specific dates into desired format.
     *
     * @param dateString string of date.
     * @return transformed string of date.
     * @throws ParseException when fails to match string of date to new pattern.
     */
    public static String parseRecordedDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")
                .withLocale(Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.toString();
    }
}
