package pixel.command;

/**
 * The PixelCommandEnum represents the available commands in the Pixel
 * application.
 */
public enum PixelCommandEnum {
    BYE("BYE"),
    LIST("LIST"),
    MARK("MARK"),
    UNMARK("UNMARK"),
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    DELETE("DELETE"),
    FIND("FIND"),
    SORT("SORT");

    private final String displayName;

    /**
     * Constructs a new PixelCommandEnum with the specified display name.
     *
     * @param displayName the display name of the command
     */
    PixelCommandEnum(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the command.
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return this.displayName;
    }
}
