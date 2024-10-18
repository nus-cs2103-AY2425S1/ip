package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidCommandException;
import beeboo.exception.InvalidIndexException;
import beeboo.task.Tasks;

/**
 * Represents a command to mark a task as done or not done in the chatbot's task list.
 */
public class MarkCommand extends Command {
    private String type;
    /**
     * Constructs an AddCommand.
     *
     * @param type    The type of task to add (e.g., "m" for mark done, "u" for mark undone).
     * @param command The command string that contains the task description and details.
     */
    public MarkCommand(String type, String command) {
        super(command);
        this.type = type;
    }

    /**
     * @param tasks tasklist of chatbot
     * @param ui ui of the chatbot
     * @param storage storage function of the chatbot
     * @throws InvalidIndexException if index is < 0 or is more than the size of tasklist
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException,
            InvalidCommandException {
        int index = Integer.parseInt(super.command);
        if (index < 0 || index > tasks.getSize()) {
            throw new InvalidIndexException("Invalid index");
        }
        switch(type) {
        case "m":
            Tasks done = tasks.markDone(index - 1);
            storage.saveItem(tasks);
            return ui.markDoneMessage(done);
        case "u":
            Tasks undone = tasks.unmarkDone(index - 1);
            storage.saveItem(tasks);
            return ui.unmarkDoneMessage(undone);
        default:
            throw new InvalidCommandException();
        }

    }


    /**
     * @return true if is exit command false if not exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
