package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_TASK_MARK_INVALID;

import java.io.File;

import hypebot.parser.task.FileTaskParser;
import hypebot.parser.task.TaskParser;
import hypebot.storage.StorageManager;
import hypebot.task.Task;

/**
 * Represents an {@code IllegalTaskStatusException} thrown when the
 * marked indicator of a {@link Task} saved in a {@link File} retrieved by the
 * {@link StorageManager} is not 0 or 1 (unmarked, marked).
 * <p>A child of {@link IllegalArgumentException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TaskParser
 * @see FileTaskParser
 */
public class IllegalTaskStatusException extends IllegalArgumentException {
    /**
     * Takes in an error message and creates a new {@code IllegalTaskStatusException}.
     *
     * @param message Error message to be outputted to user interface.
     */
    public IllegalTaskStatusException(String message) {
        super(ERROR_TASK_MARK_INVALID + message + "\n");
    }
}
