package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;
import sentinel.utils.SentinelString;

/**
 * The FindCommand class is responsible for displaying the filtered list of tasks to the user.
 * This command retrieves the current filtered list of tasks and displays them using the user interface.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand with the specified user interface and task list.
     *
     * @param ui   The user interface object for displaying messages.
     * @param list The list of tasks managed by the application.
     */
    public FindCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    /**
     * Executes the find command, which displays the filtered list of tasks to the user.
     *
     * @param input The user's input keyword string
     */
    @Override
    public String execute(String input) {
        String keyword = Parser.parseKeyword(input);
        ui.showFilteredList(list, keyword);
        return SentinelString.stringFilteredList(list, keyword);
    }
}
