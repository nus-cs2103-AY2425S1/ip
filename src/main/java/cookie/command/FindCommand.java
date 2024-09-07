package cookie.command;

import java.util.ArrayList;

import cookie.exception.CookieException;
import cookie.storage.Storage;
import cookie.task.Task;
import cookie.task.TaskList;
import cookie.ui.Ui;


public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified keyword for searching tasks.
     *
     * @param keyword the keyword to search for in the task descriptions
     */
    public FindCommand(String keyword) {
        assert !keyword.isEmpty();

        this.keyword = keyword;
    }

    /**
     * Executes the {@code FindCommand} by searching for tasks in the {@code TaskList} that
     * match the specified keyword. The method retrieves tasks that contain the keyword and
     * updates the user interface with the list of matching tasks.
     *
     * @param taskList the list of tasks to be searched
     * @param ui the user interface object used to format and print the matching tasks
     * @param storage the storage object (not used in this method, but included for command consistency)
     * @return a string containing the results of the search, including the list of tasks matching the keyword
     * @throws CookieException if an error occurs while processing the command (e.g., invalid keyword)
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws CookieException {
        ArrayList<Task> tasksMatchingKeyword = taskList.findByKeyword(this.keyword);

        String response = "Here are matching tasks in your list!\n";
        response = response + ui.printTasks(tasksMatchingKeyword);

        return response;
    }
}
