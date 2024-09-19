package hypebot.exception.missing;

import hypebot.parser.command.CommandParser;
import hypebot.parser.task.TaskParser;
import hypebot.parser.task.UiTaskParser;

/**
 * Represents a base {@link IllegalArgumentException} which all {@code MissingArgumentException}s
 * inherit from.
 * <p>A child of {@link IllegalArgumentException}.</p>
 * <p>Thrown whenever an argument for parsing input is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see MissingTaskNameException
 * @see MissingDueDateException
 * @see MissingEventTimeException
 * @see MissingSearchDateException
 * @see MissingSearchQueryException
 * @see CommandParser
 * @see TaskParser
 * @see UiTaskParser
 */
public abstract class MissingArgumentException extends IllegalArgumentException {
    public MissingArgumentException(String message) {
        super(message);
    }
}
