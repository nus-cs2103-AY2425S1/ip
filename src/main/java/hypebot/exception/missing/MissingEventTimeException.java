package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_EVENT_TIME_MISSING;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Event;

/**
 * Represents a {@code MissingEventTimeException} associated with errors resulting
 * from missing event times.
 * <p>A child of {@link MissingArgumentException}.</p>
 * <p>Thrown whenever an event time for parsing a {@link Event} is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see UiDateTimeParser
 */
public class MissingEventTimeException extends MissingArgumentException {
    /**
     * Creates a new {@code MissingEventTimeException} with a message alerting that
     * an event time for a {@link Event} is missing.
     */
    public MissingEventTimeException() {
        super(ERROR_EVENT_TIME_MISSING);
    }
}
