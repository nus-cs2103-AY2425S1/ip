package hypebot.exception.datetime;

import hypebot.command.HappeningCommand;
import hypebot.parser.command.CommandParser;

import static hypebot.common.Messages.ERROR_HAPPENING_DATE_WRONG_FORMAT;

import java.time.format.DateTimeParseException;

/**
 * Represents a {@code DateTimeParseException} associated with parsing a search date
 * for the instantiation of a {@code HappeningCommand} by the {@code CommandParser}
 * when the user enters 'happening {some_date}'.
 *
 * @see java.time.format.DateTimeParseException
 * @see HappeningCommand
 * @see CommandParser
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class HappeningSearchDateParseException extends DateTimeParseException {
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
