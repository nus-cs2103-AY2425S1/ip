package commands;

public enum CommandType {
    BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE;

    public static boolean isValidCommand(String commandWord) {
        try {
            CommandType.valueOf(commandWord.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
