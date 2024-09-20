package bot.action;

import bot.tasks.TaskList;

/**
 * Action that searches for a <code>Task</code> in the <code>TaskList</code>.
 *
 * @author mongj
 */
public class SearchTaskAction extends Action {
    private final String query;

    /**
     * Creates a new <code>UnmarkTaskAction</code>.
     *
     * @param query to search for.
     */
    public SearchTaskAction(String query) {
        this.query = query;
    }

    /**
     * Searches for matching <code>Task</code> in the <code>TaskList</code>
     * based on the given query string.
     *
     * @param taskList to use.
     * @return A list of task found.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.search(query);
    }
}
