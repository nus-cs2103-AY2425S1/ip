public enum Command {
    LIST("list"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private final String VALUE;

    Command(String value) {
        this.VALUE = value;
    }

    @Override
    public String toString() {
        return VALUE;
    }
}
