package Kita;

import Kita.Exceptions.KitaIllegalDateFormat;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatters {
    private final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final static Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    /**
     * Given a date in the format of yyyy-MM-dd, returns a LocalDate object
     *
     * @param inputStr The String in the form of yyyy-MM-dd to convert
     * @exception KitaIllegalDateFormat Invalid date format not in yyyy-MM-dd entered
     * @return LocalDate - The LocalDate object
     * */
    public static LocalDate getDateFromStr(String inputStr) {
        Matcher eventMatcher = datePattern.matcher(inputStr);
        if (eventMatcher.matches()) {
            return LocalDate.parse(eventMatcher.group(), inputFormatter);
        }
        else {
            throw new KitaIllegalDateFormat();
        }

    }

    /**
     * Given a LocalDate object, return a string in the form of "MMM DD yyyy"
     *
     * @param dateObj The LocalDate object to convert
     * @return String - The string in the form of "MMM DD yyyy"
     * */
    public static String getStrFromDate(LocalDate dateObj) {
        return dateObj.format(outputFormatter);
    }
}
