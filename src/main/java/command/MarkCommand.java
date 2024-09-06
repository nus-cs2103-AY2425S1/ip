package command;

import exceptions.AlreadyCompletedException;
import exceptions.TaskDoesNotExistException;
import task.TaskList;

/**
 * Command which marks a task as completed when executed.
 *
 * @author IsaacPangTH
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructor for<code>MarkCommand</code>.
     *
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) {
        try {
            list.complete(this.index);
            return him.Ui.sayCompleted(list.taskAt(this.index));
        } catch (AlreadyCompletedException | TaskDoesNotExistException e) {
            return him.Ui.say(e.getMessage());
        }
    }
}
