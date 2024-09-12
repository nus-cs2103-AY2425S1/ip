package ponderpika;

/**
 * Represents a command with an action and associated data for task management.
 * <p>
 * The Command class encapsulates a specific action to be performed and the associated data.
 * This allows for flexible command handling in the application.
 * </p>
 */
public class Command {

    /**
     * Enum representing the possible actions that can be performed by a command.
     */
    public enum Action { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE, PRIORITY }

    private final Action act;
    private final Object data;

    /**
     * Constructs a new Command with the specified action and data.
     *
     * @param act The Action representing the type of command.
     * @param data The data associated with the command. The type of data depends on the action.
     */
    public Command(Action act, Object data) {
        this.act = act;
        this.data = data;
    }

    public Action getAction() {
        return this.act;
    }

    public Object getData() {
        return this.data;
    }
}
