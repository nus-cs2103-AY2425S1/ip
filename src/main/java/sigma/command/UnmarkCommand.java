package sigma.command;

import sigma.exception.SigmaException;
import sigma.task.Task;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to unmark a task from the list.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Unmarks a task from the task list.
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
        if (index <= 0 || index > tasks.size()) {
            throw new SigmaException("What the skibidi? Invalid task number!");
        }
        Task item = tasks.get(index - 1);
        if (item.getStatusString() == " ") {
            Ui.throwError("What the sigma? Task already unmarked!");
        }
        item.setCompleted(false);
        return String.format("Dang, I'm going to unmark this for you:\n [%s] %s",
                item.getStatusString(),
                item.getDescription());
    }

}
