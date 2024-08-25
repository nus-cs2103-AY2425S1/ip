public enum Command {
    BYE("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), TODO("todo"), DEADLINE("deadline"),
    EVENT("event");
    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command fromString(String command) throws KilluaException {
        for (Command cmd : Command.values()) {
            if (cmd.command.equalsIgnoreCase(command)) {
                return cmd;
            }
        }
        throw new KilluaException("Invalid input: " + command);
    }
}
