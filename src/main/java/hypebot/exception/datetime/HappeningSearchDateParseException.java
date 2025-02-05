package hypebot.exception.datetime;

import static hypebot.common.Messages.ERROR_HAPPENING_DATE_WRONG_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import hypebot.command.HappeningCommand;
import hypebot.parser.command.CommandParser;
import hypebot.parser.datetime.UiDateTimeParser;

/**
 * Represents a {@code HappeningSearchDateParseException} associated
 * with parsing a {@link LocalDate} search date for the instantiation of a
 * {@link HappeningCommand} by the {@link CommandParser}
 * when the user enters 'happening {some_date}'.
 * <p>A child of {@link HypeBotDateTimeParseException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParseException
 * @see UiDateTimeParser
 */
public class HappeningSearchDateParseException extends HypeBotDateTimeParseException {
    /**
     * Takes in the parsed data that lead to the error, and index of user input that
     * caused error and creates a new HappeningSearchDateParseException.
     *
     * @param parsedData User entered data or tasks.txt data that lead to error.
     * @param errorIndex Index of data where error occurred.
     */
    public HappeningSearchDateParseException(CharSequence parsedData, int errorIndex) {
        super(ERROR_HAPPENING_DATE_WRONG_FORMAT, parsedData, errorIndex);
    }
}
