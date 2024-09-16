package fred;

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

    public String getCommandName() {
        return commandName;
    }
}

