package hypebot.parser;

import static hypebot.common.Messages.ERROR_DELETE_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_MARK_TASK_INDEX_MISSING;
import static hypebot.common.Messages.ERROR_UNMARK_TASK_INDEX_MISSING;

/**
 * Represents the IndexParser that parses all indexes user inputs in a command.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class IndexParser {
    private static final int INDEX_OFFSET = 1;

    private static int getIndexOffset(String line) throws NumberFormatException, IndexOutOfBoundsException {
        if (line.isBlank()) {
            throw new IndexOutOfBoundsException();
        }
        return Integer.parseInt(line.strip()) - INDEX_OFFSET;
    }

    /**
     * Takes in the full line user enters and parses the index (0-indexed)
     * of the Task to mark complete in the Tasklist managed by HypeBot.
     *
     * @param line Full String command entered by user.
     * @return Index of Task to mark complete in Tasklist.
     * @throws NumberFormatException If index entered is not in number format.
     */
    public static int parseMarkIndex(String line) throws NumberFormatException {
        try {
            String idxString = line.split(" ")[1];
            return getIndexOffset(idxString);
        } catch (Exception e) {
            throw new NumberFormatException(ERROR_MARK_TASK_INDEX_MISSING);
        }
    }

    /**
     * Takes in the full line user enters and parses the index (0-indexed)
     * of the Task to unmark complete in the Tasklist managed by HypeBot.
     *
     * @param line Full String command entered by user.
     * @return Index of Task to unmark complete in Tasklist.
     * @throws NumberFormatException If index entered is not in number format.
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
     * Takes in the full line user enters and parses the index (0-indexed)
     * of the Task to delete in the Tasklist managed by HypeBot.
     *
     * @param line Full String command entered by user.
     * @return Index of Task to delete in Tasklist.
     * @throws NumberFormatException If index entered is not in number format.
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
