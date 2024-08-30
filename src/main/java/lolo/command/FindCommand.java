package lolo.command;

import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * A command to find tasks by a keyword in their description.
 */
class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);
        ui.showTasksByKeyword(keyword, matchingTasks);
    }
}
