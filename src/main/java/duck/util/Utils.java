package duck.util;

import duck.data.exception.DuckException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    private Utils(){
    }
    public static LocalDateTime convertToDateTime(String dateTimeStr) throws DuckException {
        // Define the two accepted date-time formats
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        try {
            // Try to parse the input string using the first format
            return LocalDateTime.parse(dateTimeStr, formatter1);
        } catch (DateTimeParseException e) {
            // If it fails, try to parse using the second format
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);
            } catch (DateTimeParseException ex) {
                // If both fail, throw a Duck.Duck.data.exception.DuckException
                throw new DuckException("""
                        Invalid date format following the command /from /to /by!
                        Please use one of the following formats:
                        yyyy-MM-dd HHmm or yyyy/MM/dd HHmm
                       """);
            }
        }
    }

    public static boolean isCorrectUpdateFormat(String message) {
        String[] words = message.split(" ");
        return words.length == 2 && isInteger(words[1]);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
