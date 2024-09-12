package duke.additionalparsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for handling the reading and parsing of Dates inside {@link String} objects and converting
 * them to {@link LocalDate} and then reformatting them back to a new {@link String} with corrected formatting.
 */
public class DateParser {

    private List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
    );

    /**
     * Reads the input String and returns it in the desired format
     *
     * @param s the input {@link String}
     * @return reformatted {@link String} after being parsed as a {@link LocalDate} and back into the desired format
     */
    public String giveDate(String s) {
        LocalDate date = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(s, formatter);
                return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e) {
                continue; // will be caught as a null return.
            }
        }
        return null;
    }
}
