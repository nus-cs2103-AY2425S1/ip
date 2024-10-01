package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;

/**
 * The {@code FindCommand} class handles the search functionality within the task list.
 * It searches for tasks that contain a specified keyword and displays the matching tasks through the UI.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        ui.showFilteredTasks(tasks, keyword);
    }
}
