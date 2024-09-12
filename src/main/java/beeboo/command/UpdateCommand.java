package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidIndexException;
import beeboo.exception.NoDescriptionException;
import beeboo.task.Tasks;

/**
 * Represents a command to mark a task as done or not done in the chatbot's task list.
 */
public class UpdateCommand extends Command {
    /**
     * Constructs an AddCommand.
     *
     * @param command The command string that contains the task description and details.
     */
    public UpdateCommand(String command) {
        super(command);
    }

    /**
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     * @throws InvalidIndexException if index is < 0 or is more than the size of tasklist
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException , NoDescriptionException {
        String command = super.command;
        int index = command.indexOf(' ');
        if (index == - 1) {
            throw new NoDescriptionException("No description found");
        }
        int taskIndex = Integer.parseInt(command.substring(0, index));
        if (taskIndex < 0 || taskIndex > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        Tasks taskToChange = tasks.get(taskIndex - 1);
        String rest = command.substring( index + 1).trim();
        taskToChange.updateTime(rest);
        storage.saveItem(tasks);
        return ui.UpdateMessage(taskIndex, taskToChange);
    }


    /**
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
