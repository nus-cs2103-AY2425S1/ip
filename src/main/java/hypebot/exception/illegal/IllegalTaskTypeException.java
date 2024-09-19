package hypebot.exception.illegal;

import static hypebot.common.Messages.ERROR_INVALID_TASK_TYPE;

import java.io.File;

import hypebot.parser.task.FileTaskParser;
import hypebot.parser.task.TaskParser;
import hypebot.parser.task.UiTaskParser;
import hypebot.storage.StorageManager;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

/**
 * Represents an {@code IllegalTaskTypeException} thrown when the
 * type indicator of a {@link Task} saved in a {@link File} retrieved by the
 * {@link StorageManager} is not T, D, or E ({@link ToDo}, {@link Deadline}, or {@link Event}).
 * <p>A child of {@link IllegalArgumentException}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TaskParser
 * @see FileTaskParser
 * @see UiTaskParser
 */
public class IllegalTaskTypeException extends IllegalArgumentException {
    /**
     * Takes in an error message and creates a new {@code IllegalTaskTypeException}.
     *
     * @param message Error message to be outputted to user interface.
     */
    public IllegalTaskTypeException(String message) {
        super(ERROR_INVALID_TASK_TYPE + message + "\n");
    }
}
