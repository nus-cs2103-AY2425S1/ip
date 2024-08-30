package yapper.resources;

import yapper.exceptions.EmptyDescException;
import yapper.exceptions.EndingTimeException;
import yapper.exceptions.StartingTimeException;
import yapper.exceptions.YapperException;
import yapper.exceptions.YapperFormatException;

/**
 * Provides utility methods for joining strings with error handling.
 */
public class StringJoiner {

    /**
     * Joins a subset of strings from the specified start index to the end index, separated by spaces.
     * Throws exceptions if the resulting string is empty and provides context-specific error messages.
     *
     * @param texts         the array of strings to join
     * @param start     the starting index (inclusive)
     * @param end       the ending index (exclusive)
     * @param timeType  the type of time-related information being processed
     * @return the joined string
     * @throws YapperException if the joined string is empty and a specific error context is required
     */
    public static String join(String[] texts, int start, int end, YapperConcern timeType) throws YapperException {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(texts[i]);
            if (i != end - 1) {
                sb.append(' ');
            }
        }
        if (sb.isEmpty()) {
            String message = null;
            switch (timeType) {
            case DESC:
                throw new EmptyDescException("(E.g. todo [DESC], deadline [DESC] /by [DEADLINE_BY], etc)");
            case DEADLINE_BY:
                throw new EndingTimeException("(Format: deadline [DESC] /by [DEADLINE_BY])");
            case FROM:
                throw new StartingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
            case TO:
                throw new EndingTimeException("(Format: event [DESC] /from [FROM] /to [TO])");
            case KEYWORD:
                throw new EmptyDescException("(E.g. find [KEYWORD])");
            default:
                message = "(Something went wrong)";
                break;
            }
            throw new YapperFormatException(message);
        }
        return sb.toString();
    }
}
