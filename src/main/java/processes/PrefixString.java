package processes;

public enum PrefixString {
    BYE("bye"),
    LIST("list"),
    MARK("mark "),
    UNMARK("unmark "),
    TODO("todo "),
    DEADLINE("deadline "),
    EVENT("event "),
    DELETE("delete "),
    FIND("find ");

    private final String prefix;

    PrefixString (String prefix) {
        this.prefix = prefix;
    }

    public static PrefixString checkPrefixString (String input) {
        for (PrefixString item: PrefixString.values()) {

            if (item.equals(BYE)) {
                if (input.equals("bye")) {
                    return item;
                }
            } else if (item.equals(LIST)) {
                if (input.equals("list")) {
                    return item;
                }
            } else {
                if (input.startsWith(item.prefix)) {
                    return item;
                }
            }
        }
        return null;
    }

}