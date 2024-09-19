package hypebot.exception.datetime;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_WRONG_FORMAT;

import java.time.LocalDate;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Deadline;

/**
 * Represents a {@code DueDateParseException} associated with
 * the parsing of {@link LocalDate} due date for {@link Deadline} objects
 * by {@link DateTimeParser}s.
 * <p>A child of {@link HypeBotDateTimeParseException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see FileDateTimeParser
 * @see UiDateTimeParser
 */
public class DueDateParseException extends HypeBotDateTimeParseException {
    /**
     * Takes in the parsed data that lead to error, and index of user
     * input that caused error and creates a new {@code DueDateParseException}.
     *
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public DueDateParseException(CharSequence parsedData, int errorIndex) {
        super(ERROR_DEADLINE_DATE_WRONG_FORMAT, parsedData, errorIndex);
    }
}
