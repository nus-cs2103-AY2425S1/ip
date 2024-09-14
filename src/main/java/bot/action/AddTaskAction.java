package bot.action;

import bot.tasks.Task;
import bot.tasks.TaskList;

/**
 * Action that adds a <code>Task</code> to the <code>TaskList</code>.
 *
 * @author mongj
 */
public class AddTaskAction extends Action {
    private final Task task;

    /**
     * Creates a new <code>AddTaskAction</code>.
     *
     * @param task to be added.
     */
    public AddTaskAction(Task task) {
        this.task = task;
    }

    /**
     * Creates a new <code>AddTaskAction</code>.
     *
     * @param task to be added.
     * @param canUndo indicating if the action can be undone.
     */
    public AddTaskAction(Task task, boolean canUndo) {
        this.task = task;
        super.setCanUndo(canUndo);
    }

    /**
     * Adds a <code>Task</code> to the <code>TaskList</code>.
     *
     * @param taskList to add the task to.
     * @return A summary of the current <code>TaskList</code> after the <code>Task</code> is added.
     */
    @Override
    public String execute(TaskList taskList) {
        int newTaskIndex = taskList.add(task);

        // Add the reverse action to Undo
        if (canUndo()) {
            Undo.add(new DeleteTaskAction(newTaskIndex, false));
        }

        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                taskList.get(newTaskIndex).toString(),
                taskList.size()
        );
    }
}
