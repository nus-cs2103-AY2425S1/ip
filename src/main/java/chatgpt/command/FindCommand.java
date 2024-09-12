package chatgpt.command;

import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 *  The FindCommand class represents a command to find all associated task
 *  with the given keyword.
 */
public class FindCommand extends Command {
    /** Represents the keyword to search for **/
    private String keyword;

    /**
     * The constructor for a FindCommand with the given keyword.
     *
     * @param keyword to search for within the Task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * In FindCommand, it searches for Tasks that contains the keyword within the given list
     * and displays the all the found Tasks.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList results = tasks.find(keyword);
        ui.showFound(results);
    }

    /**
     * {@inheritDoc}
     *
     * In FindCommand, it will always return false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
