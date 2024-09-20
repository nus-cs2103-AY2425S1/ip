package luke.command;

/**
 * The {@code Command} class represents a user command in Luke.
 * It contains the main components of a command, including the command word,
 * associated arguments, and a mark indicating the task's completion status (if applicable).
 */
public class Command {
    private final String mark;
    private final String command;
    private final String args;

    public Command(String command) {
        this.mark = "-";
        this.command = command;
        this.args = "";
    }

    /**
     * Constructs a {@code Command} object with a mark, command word, and arguments.
     * @param mark The mark indicating task status (e.g. "X" for done, "-" for not done).
     * @param command The command word representing the user's action (e.g. "mark", "unmark", "find").
     * @param args The arguments associated with the command (e.g. task description, index).
     */
    public Command(String mark, String command, String args) {
        this.mark = mark;
        this.command = command;
        this.args = args;
    }

    public String getMark() {
        return mark;
    }

    public String getCommand() {
        return command;
    }

    public String getArgs() {
        return args;
    }
}
