package cookie.command;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;


public abstract class Command {

    /**
     * Executes the find command, which searches for tasks in the task list
     * that match specific criteria.
     *
     * @param taskList The list of tasks where the search will be performed.
     * @param ui The user interface component to display results or prompt interactions.
     * @param storage The storage system to retrieve or save task data.
     * @return A string containing the result of the find command, usually a message
     *         displaying the matching tasks or indicating no matches found.
     * @throws CookieException If there is an issue executing the command, such as
     *         invalid or expired cookies, or other related problems.
     */
    public abstract String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException;
}
