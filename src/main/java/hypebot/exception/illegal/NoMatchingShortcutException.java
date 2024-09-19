package hypebot.exception.illegal;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Task;

/**
 * Represents an {@code NoMatchingShortcutException} thrown when the
 * {@link String} of a date entry whenever a {@link Task} parsed by {@link UiDateTimeParser}
 * does not match any {@code DayShortcut}.
 * <p>A child of {@link IllegalArgumentException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see UiDateTimeParser
 */
public class NoMatchingShortcutException extends IllegalArgumentException {
    /**
     * Creates a new {@code NoMatchingShortcutException}.
     */
    public NoMatchingShortcutException() {
        super();
    }
}
