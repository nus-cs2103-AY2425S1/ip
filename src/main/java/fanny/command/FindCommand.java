package fanny.command;

import fanny.task.Task;
import fanny.task.TaskList;
import fanny.ui.Ui;

import java.util.List;

/**
 * Represents the command that handles the "find" prompt.
 */
public class FindCommand extends Command {

    /** The keyword to search for in the list of tasks. */
    public String keyword;

    /**
     * Constructs an {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for.
     *
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks that contains the keyword.
     * Prints out the filtered list that contains the keyword.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        List<Task> filteredList = list.findTasks(this.keyword);
        if (filteredList.isEmpty()) {
            ui.showMessage("Fanny:\nNo matching tasks found.");
        } else {
            ui.showMessage("Fanny:\nHere are the matching tasks in your list:");
            for (int i = 0; i < filteredList.size(); i++) {
                ui.showMessage((i + 1) + "." + filteredList.get(i).toString());
            }
        }
        ui.showHorizontalLine();
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
