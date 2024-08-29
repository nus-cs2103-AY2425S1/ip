package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

public class FindCommand extends Command {
    private final String search;

    public FindCommand(String search) {
        super(CommandType.FIND);
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        ui.printMatchingTasks(tasks.filterTasks(search));
    }
}
