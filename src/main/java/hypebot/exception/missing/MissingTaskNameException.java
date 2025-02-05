package hypebot.exception.missing;


import static hypebot.common.Messages.ERROR_TASK_NAME_EMPTY;

import hypebot.command.AddCommand;
import hypebot.parser.task.TaskParser;
import hypebot.parser.task.UiTaskParser;
import hypebot.task.Task;

/**
 * Represents a {@code MissingTaskNameException} associated with missing due dates.
 * <p>A child of {@link MissingArgumentException}.</p>
 * <p>Thrown whenever a name for parsing a {@link Task} is missing.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TaskParser
 * @see UiTaskParser
 */
public class MissingTaskNameException extends MissingArgumentException {
    /**
     * Creates a new {@code MissingSearchDateException} with a message alerting that
     * the name for a {@link Task} to add using a {@link AddCommand} is missing.
     */
    public MissingTaskNameException() {
        super(ERROR_TASK_NAME_EMPTY);
    }
}
