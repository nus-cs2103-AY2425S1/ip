package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand.
     *
     * @param keyword Keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks containing the keyword.
     *
     * @param tasks   TaskList to search within.
     * @param ui      UI to handle user interaction.
     * @param storage Storage (not used in this command).
     * @return Result message showing matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        return ui.showFoundTasks(matchingTasks);
    }
}
