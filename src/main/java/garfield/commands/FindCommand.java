package garfield.commands;

import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The FindCommand class represents a command to search for tasks containing a specific keyword
 * in the Garfield chatbot application. It extends the Command class to implement the functionality
 * for finding and displaying tasks that match the given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks containing the specified keyword in the task list.
     * The results are then displayed to the user. If no tasks match the keyword, a message indicating
     * that no matching tasks are found is shown.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The user interface component to handle displaying messages to the user.
     * @param storage The storage component, though not used in this command, is included for consistency.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String result = taskList.listKeywordTasks(keyword);
        if (result.isBlank()) {
            ui.showMessage("You don't have tasks matching that keyword in your list.");
        } else {
            ui.showMessage("Ugh, here's your matching tasks:\n\n" + result +
                    "\nCan we be done now?");
        }
    }
}
