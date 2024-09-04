package bob.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles user commands.
 */

public class Parser {

    /**
     * Parses string format of date and returns a LocalDate object.
     * @param date String format of date.
     * @return LocalDate object.
     */
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    public static LocalDate parseDate(String date) {
        List<DateTimeFormatter> inputFormatters = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),  //type 1 date format
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),  //type 2 date format
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),  //type 3 date format
                DateTimeFormatter.ofPattern("MMM dd yyyy")   //type 4 date format

        );
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {

            }
        }

        System.err.println("Invalid date format: " + date);
        System.out.println("Supported date formats are: yyyy-MM-dd, dd-MM-yyyy, dd/MM/yyyy, MMM dd yyyy");
        return null;
    }

//    public static LocalDate parseDate(String date) {
//        List<DateTimeFormatter> inputFormatters = List.of(
//                DateTimeFormatter.ofPattern("yyyy-MM-dd"),  //type 1 date format
//                DateTimeFormatter.ofPattern("dd-MM-yyyy"),  //type 2 date format
//                DateTimeFormatter.ofPattern("dd/MM/yyyy")  //type 3 date format
//        );
//
//        for (DateTimeFormatter formatter : inputFormatters) {
//            try {
//                return LocalDate.parse(date, formatter); // Return the parsed date if successful
//            } catch (DateTimeParseException e) {
//
//            }
//        }
//
//        System.err.println("Invalid date format: " + date);
//        System.out.println("Supported date formats are: yyyy-MM-dd, dd-MM-yyyy, dd/MM/yyyy");
//        return null;
//    }

}
