package chatkaki.commands;

public enum CommandTypes {
    DELETE, TODO, DEADLINE, EVENT, BYE, MARK, UNMARK, LIST, FIND, UNKNOWN;

    public static CommandTypes fromString(String command) {
        try {
            return CommandTypes.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
