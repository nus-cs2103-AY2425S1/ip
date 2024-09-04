package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;
import sigma.exception.SigmaException;
import sigma.task.Task;

/**
 * Represents the command to unmark a task from the list.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String[] split) {
        super(split);
    }

    /**
     * Unmarks a task from the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        if (split.length > 1) {
            int index = Integer.parseInt(split[1]);
            if (index > 0 && index <= tasks.size()) {
                Task item = tasks.get(index - 1);
                if (item.getStatusString() == " ") {
                    ui.throwError("What the sigma? Task already unmarked!");
                }
                item.setStatus(false);
                return ui.print(String.format("Dang, I'm going to unmark this for you:\n [%s] %s",
                        item.getStatusString(),
                        item.getDesc()));
            } else {
                throw new SigmaException("What the skibidi? Invalid task number!");
            }
        } else {
            throw new SigmaException("Bro's dreaming. Add a number bozo!");
        }
    }

}
