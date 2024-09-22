package hoodini;

/**
 * Stores all cases that user can input
 */
public enum Case {
    BYE,
    LIST,
    MARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
    UNMARK,
    SORT,
    ERROR;

    /**
     * Returns the case of the input
     * @param input String of command
     * @return Case type for case switch block
     */
    public static Case getCase(String input) {
        try {
            return Case.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Case.ERROR;
        }
    }

}
