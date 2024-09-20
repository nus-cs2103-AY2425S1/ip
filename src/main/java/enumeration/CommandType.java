package enumeration;

public enum CommandType {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    TODO("todo"),
    EVENT("event"),
    BYE("bye"),
    FIND("find");

    private final String type;

    /**
     * @param type
     */
    CommandType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
