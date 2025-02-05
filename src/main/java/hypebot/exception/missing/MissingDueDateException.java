package hypebot.exception.missing;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_MISSING;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Deadline;

/**
 * Represents a {@code MissingDueDateException} associated with missing due dates.
 * <p>A child of {@link MissingArgumentException}.</p>
 * <p>Thrown whenever a due date for parsing a {@link Deadline} is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see DateTimeParser
 * @see UiDateTimeParser
 */
public class MissingDueDateException extends MissingArgumentException {
    /**
     * Creates a new {@code MissingDueDateException} with a message alerting that
     * the due date for a {@link Deadline} is missing.
     */
    public MissingDueDateException() {
        super(ERROR_DEADLINE_DATE_MISSING);
    }
}
