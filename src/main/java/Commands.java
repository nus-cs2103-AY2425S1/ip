public enum Commands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    BYE("bye");

    private final String value;
    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Commands fromString(String command) throws CommandNotFoundException {
        for (Commands cmd : Commands.values()) {
            if (cmd.getValue().equalsIgnoreCase(command)) {
                return cmd;
            }
        }
        throw new CommandNotFoundException(command);
    }
}
