package duck.parameter;

import duck.common.Message;
import duck.data.exception.DuckException;

/**
 * Enum representing the valid targets for the sort command.
 */
public enum Target {
    ALL("all"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private static final String INVALID_TARGET_FORMAT = "Unknown target '";
    private final String keyword;


    /**
     * Constructs a target enum with the specified keyword.
     *
     * @param keyword
     */
    Target(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns the target enum based on the keyword.
     *
     * @param keyword The keyword to match.
     * @return The target enum that matches the keyword.
     * @throws IllegalArgumentException If the keyword does not match any target.
     */
    public static Target fromKeyword(String keyword) throws DuckException {
        for (Target target : values()) {
            if (target.getKeyword().equalsIgnoreCase(keyword)) {
                return target;
            }
        }
        throw new DuckException(INVALID_TARGET_FORMAT + keyword + "'.\n"
                + Message.INVALID_KEYWORD_BY_FOR_ALL
                + Message.INVALID_KEYWORD_BY_FOR_TODO
                + Message.INVALID_KEYWORD_BY_FOR_DEADLINE
                + Message.INVALID_KEYWORD_BY_FOR_EVENT);
    }
}
