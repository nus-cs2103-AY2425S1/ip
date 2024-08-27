package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.task.Tasks;
import beeboo.components.Ui;
import beeboo.exception.InvalidIndexException;

public class MarkCommand extends Command{
    String type;
    String command;
    public MarkCommand(String type, String command) {
        super(command);
        this.command = command;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int index = Integer.parseInt(command);
        if (index < 0 || index > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        switch(type) {
        case "m":
            Tasks done = tasks.markDone(index - 1);
            ui.markdoneMessage(done);
            storage.saveItem(tasks.getList());
            break;
        case "u":
            Tasks undone = tasks.unmarkDone(index - 1);
            ui.unmarkDoneMessage(undone);
            storage.saveItem(tasks.getList());
            break;
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
