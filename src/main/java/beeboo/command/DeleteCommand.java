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

    /**
     *
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     * @throws InvalidIndexException if index is < 0 or is more than the size of tasklist
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int index = Integer.parseInt(command);
        if(index <0 || index > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        Tasks removed = tasks.deleteItem(index - 1);
        ui.deleteItemMessage(removed, tasks.getSize());
        storage.saveItem(tasks);
    }

    /**
     *
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
