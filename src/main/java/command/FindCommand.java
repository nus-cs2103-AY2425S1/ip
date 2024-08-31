package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFindList(taskList.getTasks(), keyword);
    }

}
