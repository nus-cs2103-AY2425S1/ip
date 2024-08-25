package parser;

import exception.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String parseBy(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/by\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    public static String parseFrom(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    public static String parseTo(String eventString) throws ParseException {
        return parseDateTimeGeneral(
                "/to\\s+(\\d{4}-\\d{2}-\\d{2})\\s*(\\d{2}:\\d{2})*", eventString);
    }

    public static String parseRecordedDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.toString();
    }
    public static String parseDate(String eventString) throws ParseException {
        Pattern fromPattern = Pattern.compile("/from\\s+([\\w\\s]+)");
        Pattern toPattern = Pattern.compile("/to\\s+([\\w\\s]+)");
        Pattern byPattern = Pattern.compile("/by\\s+([\\w\\s]+)");

        Matcher fromMatcher = fromPattern.matcher(eventString);
        Matcher toMatcher = toPattern.matcher(eventString);
        Matcher byMatcher = byPattern.matcher(eventString);

        if (fromMatcher.find() && toMatcher.find()) {
            return "from: " + fromMatcher.group(1) + " " + "to: " + toMatcher.group(1);
        } else if (byMatcher.find()) {
            return "by: " + byMatcher.group(1);
        } else {
            throw new ParseException("the description of event is incorrect");
        }
    }
}
