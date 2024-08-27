package seedu.avo.commands;

public enum CommandName {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    SEARCH_DATE("on"),
    SEARCH_NAME("find"),
    EXIT("exit");
    private final String commandName;
    CommandName(String commandName) {
        this.commandName = commandName;
    }
    public String getName() {
        return commandName;
    }
}
