package talkabot;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import talkabot.exceptions.IncorrectFormatException;
import talkabot.exceptions.MissingDetailException;
import talkabot.exceptions.UnknownTimeException;

/**
 * Parser Class handles the conversion of input
 * for easier storage and calling of methods.
 */
public class Parser {

    /**
     * Converts date from String to LocalDate.
     *
     * @return LocalDate representation of date.
     * @throws DateTimeException If input does not contain valid date or if date is given in the wrong format.
     */
    public static LocalDate stringToDate(String s) {
        return LocalDate.parse(s);
    }

    /**
     * Converts date from LocalDate to String.
     *
     * @return String representation of date.
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Separates description and deadline based on deadline input.
     *
     * @return String array containing description and deadline.
     * @throws UnknownTimeException If input does not contain deadline.
     */
    public static String[] getDeadline(String input, int detailLength) {
        if (input.indexOf("/by ") == -1) {
            throw new UnknownTimeException("should be done by");
        }
        if (input.indexOf("/by ") <= detailLength) {
            throw new MissingDetailException("description");
        }
        if (input.length() <= input.indexOf("/by ") + 4) {
            throw new MissingDetailException("due date");
        }
        if (input.charAt(input.indexOf("/by ") - 1) != ' ') {
            throw new IncorrectFormatException();
        }
        String[] details = new String[2];
        details[0] = input.substring(detailLength, input.indexOf("/by ") - 1);
        details[1] = input.substring(input.indexOf("/by ") + 4);
        return details;
    }

    /**
     * Separates description and dates based on event input.
     *
     * @return String array containing description and dates.
     * @throws UnknownTimeException If input does not contain start and/or end dates.
     */
    public static String[] getEvent(String input, int detailLength) {
        if (input.indexOf("/from ") == -1) {
            throw new UnknownTimeException("starts");
        }
        if (input.indexOf("/to ") == -1) {
            throw new UnknownTimeException("ends");
        }
        if (input.indexOf("/from ") <= detailLength) {
            throw new MissingDetailException("description");
        }
        if (input.indexOf("/to ") <= input.indexOf("/from ") + 6) {
            throw new MissingDetailException("start date");
        }
        if (input.length() <= input.indexOf("/to ") + 4) {
            throw new MissingDetailException("end date");
        }
        if (input.charAt(input.indexOf("/from ") - 1) != ' '
            || input.charAt(input.indexOf("/to ") - 1) != ' ') {
            throw new IncorrectFormatException();
        }
        String[] details = new String[3];
        details[0] = input.substring(detailLength, input.indexOf("/from ") - 1);
        details[1] = input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ") - 1);
        details[2] = input.substring(input.indexOf("/to ") + 4);
        return details;
    }

}
