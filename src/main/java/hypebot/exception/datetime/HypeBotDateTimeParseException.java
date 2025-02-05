package hypebot.exception.datetime;

import java.time.format.DateTimeParseException;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Task;

/**
 * Represents a {@link DateTimeParseException} associated with
 * the parsing of {@link Task} objects that all {@code HypeBotDateTimeParseException}s
 * inherit from.
 * <p>A child of {@link DateTimeParseException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DueDateParseException
 * @see EventDateTimeParseException
 * @see HappeningSearchDateParseException
 * @see DateTimeParser
 * @see FileDateTimeParser
 * @see UiDateTimeParser
 */
public abstract class HypeBotDateTimeParseException extends DateTimeParseException {
    /**
     * Takes in an error message, parsed data that lead to error, and index of user
     * input that caused error and creates a new {@code HypeBotDateTimeParseException}.
     *
     * @param message Error message to be outputted to user interface.
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public HypeBotDateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
