package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

/**
 * The ByeCommand class handles the "bye" command, which is used to end the session.
 * When executed, it displays a goodbye message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new ByeCommand object with the specified UI and task list.
     *
     * @param ui   The user interface object for displaying messages.
     * @param list The list of tasks managed by the application.
     */
    public ByeCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    /**
     * Executes the "bye" command, displaying a goodbye message to the user.
     *
     * @param input The user's input string (not used in this command).
     */
    @Override
    public void execute(String input) {
        ui.showGoodbye();
    }
}
