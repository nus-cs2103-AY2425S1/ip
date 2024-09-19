package hypebot.parser.command;

import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_MISSING;

import hypebot.command.DeleteCommand;
import hypebot.command.MarkCommand;
import hypebot.command.UnmarkCommand;
import hypebot.main.HypeBot;
import hypebot.parser.Parser;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code IndexParser} that parses all {@code int} indexes
 * inputted by the user at {@link UiGuiMainWindow} for the parsing of
 * {@link MarkCommand}s, {@link UnmarkCommand}s, and {@link DeleteCommand}s.
 * <p>A child of {@link Parser}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see NumberFormatException
 */
public class IndexParser {
    /* Offset constant from user-observed 1-index to Tasklist-observed 0-index. */
    private static final int INDEX_OFFSET = 1;

    private static int getIndexOffset(String idx) throws NumberFormatException {
        return Integer.parseInt(idx.strip()) - INDEX_OFFSET;
    }

    /**
     * Takes in the full {@link String} line user from {@link UiGuiMainWindow} enters and parses
     * the index (0-indexed) of the {@link Task} to {@code mark()} complete in
     * the {@link Tasklist} managed by {@link HypeBot}.
     *
     * @param line Full {@link String} line entered by user from {@link UiGuiMainWindow}.
     * @return Index of {@link Task} to {@code mark()} complete in {@link Tasklist}.
     * @throws NumberFormatException If index entered is not in {@link Integer} format.
     */
    public static int parseMarkIndex(String line) throws NumberFormatException {
        try {
            String idxString = line.split(" ")[1];
            return getIndexOffset(idxString);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_MARK_TASK_INDEX_MISSING);
        }
    }

    /**
     * Takes in the full {@link String} line user from {@link UiGuiMainWindow} enters and parses
     * the index (0-indexed) of the {@link Task} to {@code unmark()} complete in
     * the {@link Tasklist} managed by {@link HypeBot}.
     *
     * @param line Full {@link String} line entered by user from {@link UiGuiMainWindow}.
     * @return Index of {@link Task} to {@code unmark()} complete in {@link Tasklist}.
     * @throws NumberFormatException If index entered is not in {@link Integer} format.
     */
    public static int parseUnmarkIndex(String line) throws NumberFormatException {
        try {
            String idxString = line.split(" ")[1];
            return getIndexOffset(idxString);
        } catch (Exception e) {
            throw new NumberFormatException(ERROR_UNMARK_TASK_INDEX_MISSING);
        }
    }

    /**
     * Takes in the full {@link String} line user enters from {@link UiGuiMainWindow} and parses
     * the index (0-indexed) of the {@link Task} to {@code delete()} complete in
     * the {@link Tasklist} managed by {@link HypeBot}.
     *
     * @param line Full {@link String} line entered by user from {@link UiGuiMainWindow}.
     * @return Index of {@link Task} to {@code delete()} complete in {@link Tasklist}.
     * @throws NumberFormatException If index entered is not in {@link Integer} format.
     */
    public static int parseDeleteIndex(String line) throws NumberFormatException {
        try {
            String idxString = line.split(" ")[1];
            return getIndexOffset(idxString);
        } catch (Exception e) {
            throw new NumberFormatException(ERROR_DELETE_TASK_INDEX_MISSING);
        }
    }
}
