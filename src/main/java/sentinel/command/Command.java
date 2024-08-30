package sentinel.command;

import sentinel.exception.SentinelException;
import sentinel.ui.Ui;
import sentinel.utils.SentinelList;
import sentinel.Sentinel;

/**
 * The Command class is an abstract base class for all commands in the Sentinel application.
 * Each command interacts with the user interface and the task list to perform specific operations.
 */
public abstract class Command {
    protected Ui ui;
    protected SentinelList list;

    /**
     * Constructs a Command with the specified user interface and task list.
     *
     * @param ui   The user interface object for displaying messages.
     * @param list The list of tasks managed by the application.
     */
    public Command(Ui ui, SentinelList list) {
        this.ui = ui;
        this.list = list;
    }

    /**
     * Executes the command with the given input.
     *
     * @param input The user's input string.
     * @throws SentinelException If an error occurs during command execution.
     */
    public abstract void execute(String input) throws SentinelException;

    /**
     * Factory method for creating specific Command instances based on the command type.
     *
     * @param commandType The type of command to create.
     * @param ui          The user interface object for displaying messages.
     * @param list        The list of tasks managed by the application.
     * @return The created Command instance.
     * @throws IllegalArgumentException If the command type is unknown.
     */
    public static Command createCommand(Sentinel.CommandType commandType, Ui ui, SentinelList list) {
        return switch (commandType) {
            case list -> new ListCommand(ui, list);
            case find -> new FindCommand(ui, list);
            case mark, unmark, delete -> new ModifyCommand(ui, list, commandType);
            case todo, deadline, event -> new CreateTaskCommand(ui, list, commandType);
            case help -> new HelpCommand(ui, list);
            case bye -> new ByeCommand(ui, list);
            default -> throw new IllegalArgumentException("Unknown command");
        };
    }
}
