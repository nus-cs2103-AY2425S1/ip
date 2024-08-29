package action;

import task.TaskList;

/**
 * Action to find all tasks matching a given search string.
 */
public class FindTasksAction extends Action {
    private final String searchString;

    /**
     * Constructor for a <code>FindTasksAction</code>.
     *
     * @param searchString Search string inputted by the user.
     */
    public FindTasksAction(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Returns a string of all tasks in the given <code>taskList</code> matching the <code>searchString</code>.
     *
     * @param taskList Task list to find tasks from.
     * @return String of all tasks in the task list matching the search string.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.findMatches(searchString);
    }
}
