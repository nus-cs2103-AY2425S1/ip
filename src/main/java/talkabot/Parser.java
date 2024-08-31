package talkabot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import talkabot.exceptions.UnknownTimeException;

public class Parser {

    public static LocalDate stringToDate(String s) {
        return LocalDate.parse(s);
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String[] getDeadline(String input) {
        if (input.indexOf("/by") == -1) {
            throw new UnknownTimeException("should be done by");
        }
        String[] details = new String[2];
        details[0] = input.substring(9, input.indexOf("/by") - 1);
        details[1] = input.substring(input.indexOf("/by") + 4);
        return details;
    }

    public static String[] getEvent(String input) {
        if (input.indexOf("/from") == -1) {
            throw new UnknownTimeException("starts");
        }
        if (input.indexOf("/to") == -1) {
            throw new UnknownTimeException("ends");
        }
        String[] details = new String[3];
        details[0] = input.substring(6, input.indexOf("/from") - 1);
        details[1] = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
        details[2] = input.substring(input.indexOf("/to") + 4);
        return details;
    }


}
