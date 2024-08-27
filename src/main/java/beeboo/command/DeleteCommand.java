package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.task.Tasks;
import beeboo.components.Ui;
import beeboo.exception.InvalidIndexException;

public class DeleteCommand extends Command{
    private String command;
    public DeleteCommand(String command) {
       this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int index = Integer.parseInt(command);
        if(index <0 || index > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        Tasks removed = tasks.deleteItem(index - 1);
        ui.deleteItemMessage(removed, tasks.getSize());
        storage.saveItem(tasks.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
