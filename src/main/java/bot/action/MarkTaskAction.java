package bot.action;

import bot.exceptions.InvalidTaskIdException;
import bot.tasks.Task;
import bot.tasks.TaskList;

/**
 * Action that marks a <code>Task</code> in the <code>TaskList</code> as completed.
 *
 * @author mongj
 */
public class MarkTaskAction extends Action {
    private final int index;

    /**
     * Creates a new <code>MarkTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be marked.
     */
    public MarkTaskAction(int index) {
        this.index = index;
    }

    /**
     * Creates a new <code>MarkTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be marked.
     * @param canUndo indicating if the action can be undone.
     */
    public MarkTaskAction(int index, boolean canUndo) {
        this.index = index;
        super.setCanUndo(canUndo);
    }

    /**
     * Marks a <code>Task</code> in the <code>TaskList</code> as completed.
     *
     * @param taskList to use.
     * @return A response message.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidTaskIdException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new InvalidTaskIdException(String.valueOf(index + 1));
        }

        Task markedTask = taskList.mark(index);

        // Add the reverse action to Undo
        if (canUndo()) {
            Undo.add(new UnmarkTaskAction(index, false));
        }

        return "Nice! I've marked this task as done:\n" + markedTask;
    }
}
