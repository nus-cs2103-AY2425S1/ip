package utility;

public enum Commands {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    SORT("sort");
    
    private String command;
    
    Commands(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return this.command;
    }
}
