package sigma.command;

import sigma.exception.SigmaException;
import sigma.task.Task;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand extends Command {

    public MarkCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param tasks
     * @param ui
     * @param storage
     *
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        if (commandArray.length <= 1) {
            throw new SigmaException("Bro's dreaming. Add a number bozo!");
        }
        int index = Integer.parseInt(commandArray[1]);
        if (index < 0 || index > tasks.size()) {
            throw new SigmaException("Invalid task number!");
        }
        Task item = tasks.get(index - 1);
        assert item != null : "Task cannot be null";
        if (item.getStatusString() == "X") {
            throw new SigmaException("What the sigma? Task already marked!");
        }
        item.setCompleted(true);
        return String.format("SLAYYY! I'm going to mark this done for you:\n [%s] %s",
                item.getStatusString(),
                item.getDescription());

    }

}
