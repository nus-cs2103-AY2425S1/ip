package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

/**
 * The HelpCommand class is responsible for displaying the help message to the user.
 * This command provides guidance on how to use the application and its available commands.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand with the specified user interface and task list.
     *
     * @param ui   The user interface object for displaying messages.
     * @param list The list of tasks managed by the application.
     */
    public HelpCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    /**
     * Executes the help command, which displays the help message to the user.
     *
     * @param input The user's input string (not used in this command).
     */
    @Override
    public void execute(String input) {
        ui.showHelp();
    }
}
