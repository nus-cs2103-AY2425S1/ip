package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;

/**
 * Represents a command to filter the task list, by a specific description keyword.
 */
public class FindCommand extends Command {

    String keyword;

    /**
     * Constructs a command to filter the task list, by the specified description keyword.
     *
     * @param keyword Keyword to be checked within tasks' descriptions.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes task filtering, by description keyword.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @param ui MoiMoi's user interface.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showList(tasks, this.keyword);
    }

}
