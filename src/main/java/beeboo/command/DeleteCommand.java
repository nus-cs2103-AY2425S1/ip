package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidIndexException;
import beeboo.task.Tasks;

/**
 * Represents a command to delete a task from the chatbot's task list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }


    /**
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     * @throws InvalidIndexException if index is < 0 or is more than the size of tasklist
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int index = Integer.parseInt(super.command);
        if (index < 0 || index > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        Tasks removed = tasks.deleteItem(index - 1);
        storage.saveItem(tasks);
        return ui.deleteItemMessage(removed, tasks.getSize());
    }

    /**
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
