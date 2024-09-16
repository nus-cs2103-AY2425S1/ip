package nerf.io;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Class for handling of user input.
 */
public class Parser {
    private final Scanner scanner;
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public enum DateFormatType {
        SAVE,
        PRINT
    }

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the user's input until a newline character.
     * 
     * @return user's input.
     */
    public String getInput() {
        return this.scanner.nextLine();
    }

    /**
     * Takes in a string and returns the LocalDate representation.
     * If the string is not in the form yyyy-MM-dd, null is returned.
     * 
     * @param dateTimeString string form of date.
     * @return LocalDate object representing date.
     */
    public static LocalDate parseStringToDate(String dateTimeString) {
        try {
            return LocalDate.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            System.out.println("Please use the format yyyy-MM-dd for datetime inputs");
            return null;
        }
    }

    /**
     * Returns the date as string in the requested format.
     * 
     * @param date the date object representation.
     * @param format the format requested.
     * @return the requested string representation of date.
     */
    public static String dateToString(LocalDate date, DateFormatType format) {
        if (format == DateFormatType.SAVE) {
            return date.format(Parser.SAVE_FORMAT);
        } else if (format == DateFormatType.PRINT) {
            return date.format(PRINT_FORMAT);
        } else {
            return "invalid date";
        }
    }
}
