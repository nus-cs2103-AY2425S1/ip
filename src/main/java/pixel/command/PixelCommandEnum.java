package pixel.command;

public enum PixelCommandEnum {
    BYE("BYE"),
    LIST("LIST"),
    MARK("MARK"),
    UNMARK("UNMARK"),
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    DELETE("DELETE"),
    FIND("FIND");

    private final String displayName;

    PixelCommandEnum(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}