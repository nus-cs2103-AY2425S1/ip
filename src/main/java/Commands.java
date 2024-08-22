public enum Commands {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");
    
    private String description;
    
    Commands(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
}
