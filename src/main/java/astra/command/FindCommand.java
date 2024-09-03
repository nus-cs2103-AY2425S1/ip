package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) throws AstraException {
        if (keyword.isBlank()) {
            throw new AstraException("The keyword cannot be empty.");
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(keyword);
        String msg = "Here are the matching tasks in your list:\n" + matchingTasks.toString();
        ui.display(msg);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList matchingTasks = tasks.find(keyword);
        return "Here are the matching tasks in your list:\n" + matchingTasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
