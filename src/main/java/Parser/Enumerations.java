package Parser;

/**
 * Set of constants that represents the commands that users can give to the testament chatbot
 */
public enum Enumerations {
    BYE("bye"),
    SCHEDULE("schedule"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    TASK("task");

    private final String text;
    private Enumerations(String s) {
        text = s;
    }

    /**
     * Converts a string into the corresponding Enumerations constant
     *
     * @param s User input
     * @return Enumerations constant representing user input
     */
    public static Enumerations convertString(String s) {
        for (Enumerations e : Enumerations.values()) {
            if (e.text.equalsIgnoreCase(s)) {
                return e;
            }
        }

        return Enumerations.TASK;
    }

}
