package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

/**
 * The ListCommand class is responsible for displaying the list of tasks to the user.
 * This command retrieves the current list of tasks and displays them using the user interface.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified user interface and task list.
     *
     * @param ui   The user interface object for displaying messages.
     * @param list The list of tasks managed by the application.
     */
    public ListCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    /**
     * Executes the list command, which displays the current list of tasks to the user.
     *
     * @param input The user's input string (not used in this command).
     */
    @Override
    public void execute(String input) {
        ui.showList(list);
    }
}
