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
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("please input DateTime in format yyyy-MM-dd HHmm",
                    inputDateTime, e.getErrorIndex());
        }
    }
}
