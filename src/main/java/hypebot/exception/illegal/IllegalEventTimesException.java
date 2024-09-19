package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;

import java.time.LocalDateTime;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Event;
import hypebot.task.Task;

/**
 * Represents an {@code IllegalEventTimesException} thrown when the
 * {@link LocalDateTime} start time and event time of an {@link Event} object are
 * not in a plausible chronological order, thus cannot create a {@link Task}
 * with such details.
 * <p>A child of {@link IllegalArgumentException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see FileDateTimeParser
 * @see UiDateTimeParser
 */
public class IllegalEventTimesException extends IllegalArgumentException {
    /**
     * Creates a new {@code IllegalEventTimesException} with the appropriate error message.
     */
    public IllegalEventTimesException() {
        super(ERROR_EVENT_TIMES_INORDERED);
    }
}
