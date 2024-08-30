package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword used to search for matching tasks in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the given keyword,
     * displaying the results to the user.
     *
     * @param tasks The task list to search through for matching tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system of the application (not used in this command).
     * @throws IOException If an I/O error occurs (though not typically expected in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showMatchingTasks(tasks.findTasksByKeyword(keyword));
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the find command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}