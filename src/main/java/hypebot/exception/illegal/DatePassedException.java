package hypebot.exception.illegal;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;

/**
 * Represents an {@code DatePassedException} thrown when the {@link LocalDate}
 * due date of a {@link Deadline} or {@link LocalDateTime} event time of an
 * {@link Event} object has passed, thus cannot create a {@link Task} with such details.
 * <p>A child of {@link IllegalArgumentException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see IllegalArgumentException
 * @see DateTimeParser
 * @see FileDateTimeParser
 * @see UiDateTimeParser
 */
public class DatePassedException extends IllegalArgumentException {
    /**
     * Takes in an error message and creates a new {@code DatePassedException}.
     *
     * @param message Error message to be outputted to user interface.
     */
    public DatePassedException(String message) {
        super(message);
    }
}
