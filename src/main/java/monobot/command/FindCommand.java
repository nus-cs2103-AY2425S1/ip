package monobot.command;

import monobot.exception.MonoBotException;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents find command to filter the list of tasks.
 */
public class FindCommand extends Command {
    private final String search;

    /**
     * Constructs a FindCommand with the specified string.
     *
     * @param search keyword to filter the list of tasks.
     */
    public FindCommand(String search) {
        super(CommandType.FIND);
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        ui.printMatchingTasks(tasks.filterTasks(search));
    }
}
