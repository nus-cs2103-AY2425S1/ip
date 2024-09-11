package sage.command;

public enum CommandType {
    BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT;

    public static boolean isValidCommand(String command) {
        try {
            CommandType.valueOf(command.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
