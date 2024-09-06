package monobot.command;

import monobot.exception.MonoBotException;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents find command to filter the list of tasks.
 */
public class FindCommand extends Command {
    private final String[] searchKeywords;

    /**
     * Constructs a FindCommand with the specified search keywords.
     *
     * @param searchKeywords the keywords to filter the list of tasks.
     */
    public FindCommand(String... searchKeywords) {
        super(CommandType.FIND);
        this.searchKeywords = searchKeywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        ui.printMatchingTasks(tasks.filterTasks(searchKeywords));
    }
}

