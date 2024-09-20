package bot.action;

import bot.exceptions.InvalidTaskIdException;
import bot.tasks.Task;
import bot.tasks.TaskList;

/**
 * Action that unmarks a <code>Task</code> in the <code>TaskList</code>.
 *
 * @author mongj
 */
public class UnmarkTaskAction extends Action {
    private final int index;

    /**
     * Creates a new <code>UnmarkTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be unmarked.
     */
    public UnmarkTaskAction(int index) {
        this.index = index;
    }

    /**
     * Creates a new <code>UnmarkTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be unmarked.
     * @param canUndo indicating if the action can be undone.
     */
    public UnmarkTaskAction(int index, boolean canUndo) {
        this.index = index;
        super.setCanUndo(canUndo);
    }

    /**
     * Unmarks a <code>Task</code> in the <code>TaskList</code>.
     *
     * @param taskList to use.
     * @return A response message.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidTaskIdException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new InvalidTaskIdException(String.valueOf(index + 1));
        }

        Task unmarkedTask = taskList.unmark(index);

        // Add the reverse action to Undo
        if (canUndo()) {
            Undo.add(new MarkTaskAction(index, false));
        }

        return "OK, I've marked this task as not done yet:\n" + unmarkedTask;
    }
}
