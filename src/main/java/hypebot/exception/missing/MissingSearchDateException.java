package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_HAPPENING_DATE_MISSING;

import hypebot.command.HappeningCommand;
import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;

/**
 * Represents a {@code MissingSearchDateException} associated with errors resulting
 * from missing search dates.
 * <p>A child of {@link MissingArgumentException}.</p>
 * <p>Thrown whenever a search date when parsing a {@link HappeningCommand} is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see UiDateTimeParser
 */
public class MissingSearchDateException extends MissingArgumentException {
    /**
     * Creates a new {@code MissingSearchDateException} with a message alerting that
     * a search date for a {@link HappeningCommand} is missing.
     */
    public MissingSearchDateException() {
        super(ERROR_HAPPENING_DATE_MISSING);
    }
}
