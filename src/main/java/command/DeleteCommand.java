package command;

import exceptions.TaskDoesNotExistException;
import task.Task;
import task.TaskList;

/**
 * Command which deletes a task when executed.
 *
 * @author IsaacPangTH
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for<code>DeleteCommand</code>.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        try {
            String task = list.delete(index);
            return him.Ui.sayDeleted(task);
        } catch (TaskDoesNotExistException e) {
            return him.Ui.say(e.getMessage());
        }
    }
}
