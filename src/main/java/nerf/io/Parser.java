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
     * Return the date as string in the requested format.
     * @param date the date object representation.
     * @param save boolean for toggling string pattern.
     * @return the requested string representation of date.
     */
    public static String dateToString(LocalDate date,boolean save){
        if (save){
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
