package hypebot.exception.datetime;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_WRONG_FORMAT;

/**
 * Represents a DateTimeParseException associated with the initialisation of Deadline objects.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class DueDateParseException extends HypeBotDateTimeParseException {
    /**
     * Takes in the parsed data that lead to error, and index of user
     * input that caused error and creates a new DueDateParseException.
     *
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public DueDateParseException(CharSequence parsedData, int errorIndex) {
        super(ERROR_DEADLINE_DATE_WRONG_FORMAT, parsedData, errorIndex);
    }
}
