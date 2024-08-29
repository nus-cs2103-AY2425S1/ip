package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.unmarkTask(index);
        ui.printUnmarkedTask(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
