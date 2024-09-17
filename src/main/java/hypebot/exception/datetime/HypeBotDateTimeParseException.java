package hypebot.exception.datetime;

import java.time.format.DateTimeParseException;

/**
 * Represents a DateTimeParseException associated with the initialisation of Task objects.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public abstract class HypeBotDateTimeParseException extends DateTimeParseException {
    /**
     * Takes in an error message, parsed data that lead to error, and index of user
     * input that caused error and creates a new EventDateTimeParseException.
     *
     * @param message Error message to be outputted to user interface.
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public HypeBotDateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
