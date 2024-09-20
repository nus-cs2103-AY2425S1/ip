package bot.action;

import bot.exceptions.InvalidTaskIdException;
import bot.tasks.Task;
import bot.tasks.TaskList;

/**
 * Action that deletes a <code>Task</code> from the <code>TaskList</code>.
 *
 * @author mongj
 */
public class DeleteTaskAction extends Action {
    private final int index;

    /**
     * Creates a new <code>DeleteTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be deleted.
     */
    public DeleteTaskAction(int index) {
        this.index = index;
    }

    /**
     * Creates a new <code>DeleteTaskAction</code>.
     *
     * @param index of the <code>Task</code> to be deleted.
     * @param canUndo indicating if the action can be undone.
     */
    public DeleteTaskAction(int index, boolean canUndo) {
        this.index = index;
        super.setCanUndo(canUndo);
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code>.
     *
     * @param taskList to use.
     * @return A summary of the current <code>TaskList</code> after the <code>Task</code> is deleted.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidTaskIdException {
        if (index < 0 || index > taskList.size() - 1) {
            throw new InvalidTaskIdException(String.valueOf(index + 1));
        }
        Task deletedTask = taskList.remove(index);

        // Add the reverse action to Undo
        if (canUndo()) {
            Undo.add(new AddTaskAction(deletedTask, false));
        }

        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                deletedTask.toString(),
                taskList.size()
        );
    }
}
