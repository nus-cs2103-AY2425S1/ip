package sumode.util;

public enum Command {
    BYE,
    EXIT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * Returns the expected format of input into chatbot SumoDE for various command.
     * @return expected input format of each command
     */
    public String expectedFormat() {
        return switch (this) {
            case MARK, UNMARK, DELETE -> this + " " + "<task index>";
            case TODO -> this + " <task name>";
            case DEADLINE -> this + " <task name> /by <date>";
            case EVENT -> this + " <task name> /from <date> /to <date>";
            default -> this.toString();
        };
    }
}
