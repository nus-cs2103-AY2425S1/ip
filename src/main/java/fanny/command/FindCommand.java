package fanny.command;

import java.util.List;

import fanny.task.Task;
import fanny.task.TaskList;
import fanny.ui.Ui;


/**
 * Represents the command that handles the "find" prompt.
 */
public class FindCommand extends Command {

    /** The keyword to search for in the list of tasks. */
    private String keyword;

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
     * @return The message to be displayed after executing the command.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        String message = "";

        try {
            List<Task> filteredList = list.findTasks(this.keyword);
            ui.showHorizontalLine();
            message = ui.showFindTaskMsg(filteredList);
        } catch (IllegalArgumentException e) {
            message = ui.showMessage(e.getMessage());
        }
        ui.showHorizontalLine();

        return message;
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
