package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTask(keyword);
    }
}
