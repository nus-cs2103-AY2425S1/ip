package command;

import java.util.ArrayList;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

/**
 * Represents a command to find tasks that contain a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that contain the keyword and shows them to the user.
     *
     * @param tasks The task list to search for tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException If there is an error finding the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        assert keyword != null : "Keyword should not be null";
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatching(matchingTasks);
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
