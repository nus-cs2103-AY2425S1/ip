package ScoobyDoo.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ScoobyDoo.exception.InputFormatException;

public class EventParser {


    /**
     * Parses the /from and /to dates of an event from the input string.
     *
     * @param input The input string containing the event information.
     * @return An array of LocalDateTime objects, where the first element is the /from date and the second is the /to date.
     * @throws InputFormatException If the input format is invalid or the date parsing fails.
     */
    public static LocalDateTime[] getEventFromAndToDate(String input) throws InputFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitFromTo = input.split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        try {
            LocalDateTime[] fromTo = {LocalDateTime.parse(splitFromTo[1].trim(), formatter),
                    LocalDateTime.parse(splitFromTo[2].trim(),formatter)};
            return fromTo;
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }
    }
}
