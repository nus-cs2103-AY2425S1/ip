package Parser;

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

    public static Enumerations convertString(String s) {
        for (Enumerations e : Enumerations.values()) {
            if (e.text.equalsIgnoreCase(s)) {
                return e;
            }
        }

        return Enumerations.TASK;
    }

}
