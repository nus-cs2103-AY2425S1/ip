package bot.action;

import bot.tasks.TaskList;

/**
 * Action that lists all <code>Task</code> in the <code>TaskList</code>.
 *
 * @author mongj
 */
public class ListTaskAction extends Action {
    /**
     * Lists all <code>Task</code> in the <code>TaskList</code>.
     *
     * @param taskList to use.
     * @return A list of tasks.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.toString();
    }
}
