package cancelgpt.command;

/**
 * Represents commands enums for the CancelGPT chatbot.
 */
public enum Command {
    LIST("list"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    BYE("bye");

    private final String value;

    /**
     * Initialises a Command enum with value as its string representation.
     *
     * @param value the string value of the Command
     */
    Command(String value) {
        this.value = value;
    }

    /**
     * Returns the Command's value
     *
     * @return the Command's value
     */
    @Override
    public String toString() {
        return value;
    }
}
