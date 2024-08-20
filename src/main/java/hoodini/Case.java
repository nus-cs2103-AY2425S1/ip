package hoodini;

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
    ERROR;

    public static Case getCase(String input) {
        try {
            return Case.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Case.ERROR;
        }
    }

}
