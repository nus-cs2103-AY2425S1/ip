package ScoobyDoo.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ScoobyDoo.exception.InputFormatException;

public class DeadlineParser {
    /**
     * Parses the date of a deadline task from the input string.
     *
     * @param input The input string containing the deadline task information.
     * @return The LocalDateTime object representing the deadline date.
     * @throws InputFormatException If the input format is invalid or the date parsing fails.
     */
    public static LocalDateTime getDeadlineDate(String input) throws InputFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitBy = input.split("/by",2);
        if (splitBy.length != 2) {
            throw new InputFormatException("Oops! I need a /by regex to save your deadline task");
        }
        try {
            return LocalDateTime.parse(splitBy[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }
    }
}
