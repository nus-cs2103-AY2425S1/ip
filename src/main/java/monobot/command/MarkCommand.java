package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;
import monobot.exception.MonoBotException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        tasks.markTask(index);
        ui.printMarkedTask(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
