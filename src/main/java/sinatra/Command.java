package sinatra;

/**
 * Enum representing the different commands in the Sinatra application.
 */
public enum Command {
    LIST("list"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    UNKNOWN("");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    /**
     * Returns the Command enum constant corresponding to the given text.
     * If no matching command is found, returns UNKNOWN.
     *
     * @param s the input text
     * @return the corresponding Command enum constant
     */
    public static Command getCommandFromString(String s) {
        for (Command c : Command.values()) {
            if (s.startsWith(c.command)) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public static String getCommandContentsFromString(String s) {
        System.out.println(s);
        for (Command c : Command.values()) {
            if (s.startsWith(c.command)) {
                if (s.length() == c.command.length()) {
                    return "";
                }
                String output = s.substring(c.command.length() + 1);
                return output;
            }
        }
        return "";

    }

    public String getCommand() {
        return command;
    }

}
