package duck.parameter;

import duck.common.Message;
import duck.data.exception.DuckException;

/**
 * Enum representing the valid sorting criteria (/by) for the sort command.
 */
public enum By {
    DESCRIPTION("description"),
    TYPE("type"),
    START("start"),
    END("end"),
    DEADLINE("deadline");

    public static final String INVALID_BY_FORMAT = "Unknown sorting criterion: ";
    private final String keyword;

    /**
     * Constructs a By enum with the specified keyword.
     *
     * @param keyword The keyword associated with the By enum.
     */
    By(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns the By enum based on the keyword.
     *
     * @param keyword The keyword to match.
     * @return The By enum that matches the keyword.
     * @throws IllegalArgumentException If the keyword does not match any By enum.
     */
    public static By fromKeyword(String keyword) throws DuckException {
        for (By by : values()) {
            if (by.getKeyword().equalsIgnoreCase(keyword)) {
                return by;
            }
        }
        throw new DuckException(INVALID_BY_FORMAT + keyword + ".\n"
                + Message.INVALID_KEYWORD_BY_FOR_ALL
                + Message.INVALID_KEYWORD_BY_FOR_TODO
                + Message.INVALID_KEYWORD_BY_FOR_DEADLINE
                + Message.INVALID_KEYWORD_BY_FOR_EVENT);
    }
}
