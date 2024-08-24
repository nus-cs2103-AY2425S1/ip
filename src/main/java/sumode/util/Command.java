package sumode.util;

/**
 * Enum of all valid command
 * @see Command#BYE
 * @see Command#EXIT
 * @see Command#LIST
 * @see Command#MARK
 * @see Command#UNMARK
 * @see Command#DELETE
 * @see Command#TODO
 * @see Command#DEADLINE
 * @see Command#EVENT
 */
public enum Command {
    /**
     * End the conversation.
     */
    BYE,
    /**
     * End the conversation.
     */
    EXIT,
    /**
     * List all taskings.
     */
    LIST,
    /**
     * Mark the task.
     * Expected syntax: mark {@code <}task index{@code >}
     */
    MARK,
    /**
     * Unmark the task.
     * Expected syntax: unmark {@code <}task index{@code >}
     */
    UNMARK,
    /**
     * Delete the task.
     * Expected syntax: delete {@code <}task index{@code >}
     */
    DELETE,
    /**
     * Add a todo task.
     * Expected syntax: todo {@code <}task name{@code >}
     */
    TODO,
    /**
     * Add a deadline task.
     * Expected syntax: deadline {@code <}task name{@code >} /by {@code <}date{@code >}
     */
    DEADLINE,
    /**
     * Add a event task.
     * Expected syntax: event {@code <}task name{@code >} /from {@code <}date{@code >} /to {@code <}date{@code >}
     */
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
