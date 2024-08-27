package Kita;

import Kita.Exceptions.KitaIllegalDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatters {
    private final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final static Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    public static LocalDate getDateFromStr(String inputStr) {
        Matcher eventMatcher = datePattern.matcher(inputStr);
        if (eventMatcher.matches()) {
            return LocalDate.parse(eventMatcher.group(), inputFormatter);
        }
        else {
            throw new KitaIllegalDateFormat();
        }

    }

    public static String getStrFromDate(LocalDate dateObj) {
        return dateObj.format(outputFormatter);
    }
}
