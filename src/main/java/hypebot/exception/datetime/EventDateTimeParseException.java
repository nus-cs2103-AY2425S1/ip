package hypebot.exception.datetime;

import static hypebot.common.Messages.ERROR_EVENT_TIME_WRONG_FORMAT;

import java.time.LocalDateTime;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Event;

/**
 * Represents a {@code EventDateTimeParseException} associated with
 * the parsing of {@link LocalDateTime} event times of {@link Event}
 * objects by {@link DateTimeParser}s.
 * <p>A child of {@link HypeBotDateTimeParseException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see FileDateTimeParser
 * @see UiDateTimeParser
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
