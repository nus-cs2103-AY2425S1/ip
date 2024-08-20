public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, ADD, UNKNOWN;

    /**
     * Converts a string command into a Command enum.
     *
     * @param command The command string.
     * @return The corresponding Command enum, or UNKNOWN if the command is unrecognized.
     */
    public static Command fromString(String command) {
        if (command.equals("bye")) {
            return BYE;
        } else if (command.equals("list")) {
            return LIST;
        } else if (command.startsWith("mark")) {
            return MARK;
        } else if (command.startsWith("unmark")) {
            return UNMARK;
        } else if (command.startsWith("delete")) {
            return DELETE;
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            return ADD;
        } else {
            return UNKNOWN;
        }
    }
}
