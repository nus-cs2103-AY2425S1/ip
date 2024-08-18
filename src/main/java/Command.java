public enum Command {
    DELETE, TODO, DEADLINE, EVENT, BYE, MARK, UNMARK, LIST, UNKNOWN;

    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
