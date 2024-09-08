package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;

/**
 * Represents a command by user to find tasks that match a keyword.
 */
public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        assert tasks != null : "TaskList cannot be null";
        TaskList matchingTasks = tasks.findTask(keywords);
        assert matchingTasks != null : "Matching tasks cannot be null";
        return ui.showMatchingTasks(matchingTasks);
    }
}
