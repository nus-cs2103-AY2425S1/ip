package park.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DtFormatter {

    /**
     * Changes the format of a DateTime represented by a String.
     *
     * @param inputDateTime String representing DateTime in format "yyyy-MM-dd HHmm".
     * @return String representing inputDateTime in format "MMM dd yyyy, h:mma".
     */
    public static String format(String inputDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        try {
            // Check whether inputDateTime is already in output format
            // Skips formatting when reading and creating Task objects from storage file
            LocalDateTime.parse(inputDateTime, outputFormatter);
            return inputDateTime;
        } catch (DateTimeParseException normalInputException) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);
                return dateTime.format(outputFormatter);
            } catch (DateTimeParseException formatException) {
                throw new DateTimeParseException("please input DateTime in format yyyy-MM-dd HHmm",
                        inputDateTime, formatException.getErrorIndex());
            }
        }
    }
}
