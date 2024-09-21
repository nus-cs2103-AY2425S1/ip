package fred;

/**
 * Enum representing various commands that can be issued in the application.
 * Each command has a corresponding string representation.
 */
public enum Command {
    EXIT("bye"),
    LIST_TASKS("list"),
    MARK_TASK("mark"),
    UNMARK_TASK("unmark"),
    DELETE_TASK("delete"),
    FIND_TASK("find"),
    ADD_TODO_TASK("todo"),
    ADD_DEADLINE_TASK("deadline"),
    ADD_EVENT_TASK("event"),
    TAG_TASK("tag");

    private final String commandName;

    private Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Returns the string representation of the command.
     *
     * @return the command name
     */
    public String getCommandName() {
        return commandName;
    }
}
