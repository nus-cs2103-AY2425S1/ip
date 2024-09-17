package hypebot.exception.datetime;

import static hypebot.common.Messages.ERROR_EVENT_TIME_WRONG_FORMAT;

/**
 * Represents a DateTimeParseException associated with the initialisation of Event objects.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class EventDateTimeParseException extends HypeBotDateTimeParseException {
    /**
     * Takes in the parsed data that lead to error, and index of user input that
     * caused error and creates a new {@code EventDateTimeParseException}.
     *
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public EventDateTimeParseException(CharSequence parsedData, int errorIndex) {
        super(ERROR_EVENT_TIME_WRONG_FORMAT, parsedData, errorIndex);
    }
}
