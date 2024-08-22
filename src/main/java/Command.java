enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN;

    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}


